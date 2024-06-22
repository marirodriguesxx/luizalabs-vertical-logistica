package com.example.vertical_logistics.application.service;

import com.example.vertical_logistics.adapter.out.persistence.OrderRepository;
import com.example.vertical_logistics.adapter.out.persistence.ProductRepository;
import com.example.vertical_logistics.adapter.out.persistence.UserRepository;
import com.example.vertical_logistics.application.port.in.FileUploadUseCase;
import com.example.vertical_logistics.domain.model.Order;
import com.example.vertical_logistics.domain.model.Product;
import com.example.vertical_logistics.domain.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileUploadService implements FileUploadUseCase {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public FileUploadService(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        List<User> users = parseFile(file);
        saveUsersWithOrdersAndProducts(users);
    }

    private void saveUsersWithOrdersAndProducts(List<User> users) {
        for (User user : users) {
            userRepository.save(user);
            for (Order order : user.getOrders()) {
                order.setUser(user);
                orderRepository.save(order);
                for (Product product : order.getProducts()) {
                    product.setOrder(order);
                    productRepository.save(product);
                }
            }
        }
    }

    @Override
    public List<User> getOrders(Integer orderId, String startDate, String endDate) {
        if (orderId != null) {
            return userRepository.findByOrderId(orderId);
        }
        if (startDate != null && endDate != null) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return userRepository.findByDateRange(start, end);
        }
        return userRepository.findAll();
    }

    private List<User> parseFile(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        Map<Integer, User> userMap = new HashMap<>();
        Map<Integer, Order> orderMap = new HashMap<>();
        Map<Integer, Product> productMap = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            int userId = Integer.parseInt(line.substring(0, 10).trim());
            String userName = line.substring(10, 55).trim();
            int orderId = Integer.parseInt(line.substring(55, 65).trim());
            int productId = Integer.parseInt(line.substring(65, 75).trim());
            BigDecimal productValue = new BigDecimal(line.substring(75, 87).trim());
            String dateStr = line.substring(87, 95).trim();
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));

            User user = userMap.computeIfAbsent(userId, id -> {
                User newUser = new User();
                newUser.setUserId(id);
                newUser.setName(userName);
                newUser.setOrders(new ArrayList<>());
                return newUser;
            });

            Order order = orderMap.computeIfAbsent(orderId, id -> {
                Order newOrder = new Order();
                newOrder.setOrderId(id);
                newOrder.setDate(date);
                newOrder.setUser(user);  // Configura a referência de User na ordem
                newOrder.setProducts(new ArrayList<>());
                user.getOrders().add(newOrder);
                return newOrder;
            });

            Product product = productMap.computeIfAbsent(productId, id -> {
                Product newProduct = new Product();
                newProduct.setProductId(id);
                newProduct.setValue(productValue);
                newProduct.setOrder(order);  // Configura a referência de Order no produto
                return newProduct;
            });

            order.getProducts().add(product);
            order.setTotal(order.getProducts().stream()
                    .map(Product::getValue)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        return new ArrayList<>(userMap.values());
    }
}
