# Estrutura de Arquivos do Projeto Vertical Logistics

```plaintext
vertical_logistics/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── vertical_logistics/
│   │   │               ├── VerticalLogisticsApplication.java
│   │   │               ├── adapter/
│   │   │               │   └── in/
│   │   │               │       └── web/
│   │   │               │           ├── FileUploadController.java
│   │   │               │           ├── OrderController.java
│   │   │               │           └── UserController.java
│   │   │               │   └── out/
│   │   │               │       └── persistence/
│   │   │               │           ├── OrderRepository.java
│   │   │               │           ├── ProductRepository.java
│   │   │               │           └── UserRepository.java
│   │   │               ├── application/
│   │   │               │   ├── dto/
│   │   │               │   │   ├── OrderDTO.java
│   │   │               │   │   ├── ProductDTO.java
│   │   │               │   │   └── UserDTO.java
│   │   │               │   ├── mapper/
│   │   │               │   │   ├── OrderMapper.java
│   │   │               │   │   ├── ProductMapper.java
│   │   │               │   │   └── UserMapper.java
│   │   │               │   ├── port/
│   │   │               │   │   ├── in/
│   │   │               │   │   │   └── FileUploadUseCase.java
│   │   │               │   │   └── out/
│   │   │               │   │       └── FileRepository.java
│   │   │               │   └── service/
│   │   │               │       ├── FileUploadService.java
│   │   │               │       ├── OrderService.java
│   │   │               │       ├── ProductService.java
│   │   │               │       └── UserService.java
│   │   │               └── domain/
│   │   │                   └── model/
│   │   │                       ├── Order.java
│   │   │                       ├── Product.java
│   │   │                       └── User.java
│   │   └── resources/
│   │       ├── application.properties
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── vertical_logistics/
│       │               ├── VerticalLogisticsApplicationTests.java
│       │               ├── application/
│       │               │   └── service/
│       │               │       ├── FileUploadServiceTest.java
│       │               │       ├── OrderServiceTest.java
│       │               │       ├── ProductServiceTest.java
│       │               │       └── UserServiceTest.java
│       │               ├── adapter/
│       │               │   └── in/
│       │               │       └── web/
│       │               │           ├── FileUploadControllerTest.java
│       │               │           ├── OrderControllerTest.java
│       │               │           └── UserControllerTest.java
│       └── resources/
│           └── application-test.properties
└── pom.xml
