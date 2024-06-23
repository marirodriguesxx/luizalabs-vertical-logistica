package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.UserRepository;
import com.example.vertical_logistics.application.dto.OrderDTO;
import com.example.vertical_logistics.application.dto.ProductDTO;
import com.example.vertical_logistics.application.dto.UserDTO;
import com.example.vertical_logistics.application.port.in.FileUploadUseCase;
import com.example.vertical_logistics.domain.model.User;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileUploadService implements FileUploadUseCase {

    private final UserRepository userRepository;
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    @Getter
    private  List<UserDTO> userDTOs = new ArrayList<>();

    public FileUploadService(UserRepository userRepository, UserService userService, OrderService orderService, ProductService productService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    @Transactional
    public void uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        userDTOs = parseFile(file);
        saveUsersWithOrdersAndProducts(userDTOs);
    }

    private void saveUsersWithOrdersAndProducts(List<UserDTO> userDTOs) {
        for (UserDTO userDTO : userDTOs) {
            User user = userService.saveUser(userDTO);

            userDTO.getOrders().forEach(orderDTO -> {
                var order = orderService.saveOrder(orderDTO, user);

                orderDTO.getProducts().forEach(productDTO -> {
                    productService.saveProduct(productDTO, order);
                });
            });
        }
    }

    @Override
    public List<UserDTO> filterUsersByOrderId(Integer orderId) {
        return userDTOs.stream()
                .filter(userDTO -> userDTO.getOrders().stream().anyMatch(orderDTO -> Objects.equals(orderDTO.getOrderId(), orderId)))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> filterUsersByDateRange(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return userDTOs.stream()
                .filter(userDTO -> userDTO.getOrders().stream().anyMatch(orderDTO -> {
                    LocalDate orderDate = orderDTO.getDate();
                    return (orderDate.isEqual(start) || orderDate.isAfter(start)) &&
                            (orderDate.isEqual(end) || orderDate.isBefore(end));
                }))
                .collect(Collectors.toList());
    }

    private List<UserDTO> parseFile(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        Map<Integer, UserDTO> userMap = new HashMap<>();
        Map<Integer, OrderDTO> orderMap = new HashMap<>();
        Map<Integer, ProductDTO> productMap = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            int userId = Integer.parseInt(line.substring(0, 10).trim());
            String userName = line.substring(10, 55).trim();
            int orderId = Integer.parseInt(line.substring(55, 65).trim());
            int productId = Integer.parseInt(line.substring(65, 75).trim());
            BigDecimal productValue = new BigDecimal(line.substring(75, 87).trim());
            String dateStr = line.substring(87, 95).trim();
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));

            UserDTO userDTO = userMap.computeIfAbsent(userId, id -> {
                UserDTO newUser = new UserDTO();
                newUser.setUserId(id);
                newUser.setName(userName);
                newUser.setOrders(new ArrayList<>());
                return newUser;
            });

            OrderDTO orderDTO = orderMap.computeIfAbsent(orderId, id -> {
                OrderDTO newOrder = new OrderDTO();
                newOrder.setOrderId(id);
                newOrder.setDate(date);
                newOrder.setProducts(new ArrayList<>());
                userDTO.getOrders().add(newOrder);
                return newOrder;
            });

            ProductDTO productDTO = productMap.computeIfAbsent(productId, id -> {
                ProductDTO newProduct = new ProductDTO();
                newProduct.setProductId(id);
                newProduct.setValue(productValue);
                return newProduct;
            });

            orderDTO.getProducts().add(productDTO);
            orderDTO.setTotal(orderDTO.getProducts().stream()
                    .map(ProductDTO::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        return new ArrayList<>(userMap.values());
    }
}
