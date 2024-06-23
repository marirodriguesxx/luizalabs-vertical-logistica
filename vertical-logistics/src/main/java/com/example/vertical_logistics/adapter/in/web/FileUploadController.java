package com.example.vertical_logistics.adapter.in.web;

import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.application.port.in.FileUploadUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final FileUploadUseCase fileUploadUseCase;

    public FileUploadController(FileUploadUseCase fileUploadUseCase) {
        this.fileUploadUseCase = fileUploadUseCase;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileUploadUseCase.uploadFile(file);
            return ResponseEntity.status(201).body("File uploaded successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file due to an I/O error.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload file due to an unexpected error.");
        }
    }

    @GetMapping("/getJson")
    public ResponseEntity<List<UserDTO>> getUserDTOs() {
        try {
            List<UserDTO> users = fileUploadUseCase.getUserDTOs();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/ordersById")
    public ResponseEntity<List<UserDTO>>  getOrdersById(@RequestParam(required = false) Integer orderId) {
        try {
            List<UserDTO> users = fileUploadUseCase.filterUsersByOrderId(orderId);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/ordersByDate")
    public ResponseEntity<List<UserDTO>>  getOrdersById(@RequestParam(required = false) String startDate,
                                                   @RequestParam(required = false) String endDate) {
        try {
            List<UserDTO> users = fileUploadUseCase.filterUsersByDateRange(startDate,endDate);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
