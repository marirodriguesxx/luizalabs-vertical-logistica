package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileUploadServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private FileUploadService fileUploadService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUploadFile() throws IOException {
        String content = "0000000088                             Terra Daniel DDS00000008360000000003     1899.0220210909\n"
                + "0000000103                                 Gail Bradtke00000009660000000005     1564.2120210507\n"
                + "0000000083                          Frances Satterfield00000007910000000006      224.7520211122\n"
                + "0000000141                                  Lloyd Mante00000013040000000001     1760.0120211012\n"
                + "0000000177                            Dr. Patrina Frami00000016320000000002     1045.1320210310";
        MultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", content.getBytes());

        User user1 = new User();
        user1.setUserId(88);
        user1.setName("Terra Daniel DDS");

        User user2 = new User();
        user2.setUserId(103);
        user2.setName("Gail Bradtke");

        User user3 = new User();
        user3.setUserId(83);
        user3.setName("Frances Satterfield");

        User user4 = new User();
        user4.setUserId(141);
        user4.setName("Lloyd Mante");

        User user5 = new User();
        user5.setUserId(177);
        user5.setName("Dr. Patrina Frami");

        when(userService.saveUser(any(UserDTO.class))).thenReturn(user1).thenReturn(user2).thenReturn(user3).thenReturn(user4).thenReturn(user5);

        fileUploadService.uploadFile(file);

        List<UserDTO> userDTOs = fileUploadService.getUserDTOs();
        assertEquals(5, userDTOs.size());

        verify(userService, times(5)).saveUser(any(UserDTO.class));
        verify(orderService, times(5)).saveOrder(any(), any(User.class));
        verify(productService, times(5)).saveProduct(any(), any());
    }

    @Test
    public void testUploadFile_EmptyFile() {
        MultipartFile file = new MockMultipartFile("file", "empty.txt", "text/plain", new byte[0]);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fileUploadService.uploadFile(file);
        });

        assertEquals("File is empty", exception.getMessage());
    }
}
