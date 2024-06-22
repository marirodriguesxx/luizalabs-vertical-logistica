package com.example.vertical_logistics.application.port.in;

import com.example.vertical_logistics.application.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileUploadUseCase {
    void uploadFile(MultipartFile file) throws IOException;
    List<UserDTO> getOrders(Integer orderId, String startDate, String endDate);
}
