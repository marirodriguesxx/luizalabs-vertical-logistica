package com.example.vertical_logistics.adapter.in.web;

import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.application.port.in.FileUploadUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FileUploadController.class)
public class FileUploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileUploadUseCase fileUploadUseCase;

    @Test
    void testUploadFile_Success() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());

        Mockito.doNothing().when(fileUploadUseCase).uploadFile(any(MockMultipartFile.class));

        mockMvc.perform(multipart("/api/files/upload").file(file))
                .andExpect(status().isCreated())
                .andExpect(content().string("File uploaded successfully."));
    }

    @Test
    void testUploadFile_BadRequest() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());

        Mockito.doThrow(new IllegalArgumentException("Invalid file")).when(fileUploadUseCase).uploadFile(any(MockMultipartFile.class));

        mockMvc.perform(multipart("/api/files/upload").file(file))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid file"));
    }

    @Test
    void testUploadFile_ServerError() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());

        Mockito.doThrow(new IOException("I/O error")).when(fileUploadUseCase).uploadFile(any(MockMultipartFile.class));

        mockMvc.perform(multipart("/api/files/upload").file(file))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to upload file due to an I/O error."));
    }

    @Test
    void testGetUserDTOs() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1);
        userDTO.setName("John Doe");

        List<UserDTO> users = Collections.singletonList(userDTO);

        Mockito.when(fileUploadUseCase.getUserDTOs()).thenReturn(users);

        mockMvc.perform(get("/api/files/getJson"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetUserDTOs_ServerError() throws Exception {
        Mockito.when(fileUploadUseCase.getUserDTOs()).thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/api/files/getJson"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$").isEmpty());
    }
}
