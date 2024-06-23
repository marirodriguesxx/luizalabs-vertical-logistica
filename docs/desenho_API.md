```plaintext
+-------------------+
| Presentation      |
|                   |
| +----------------+|
| |UserController  || <---> UserService
| |OrderController || <---> OrderService
| |FileUploadCtrl  || <---> FileUploadService
| +----------------+|
+-------------------+

+-------------------+
| Service           |
|                   |
| +----------------+|
| |UserService     || <---> UserRepository
| |OrderService    || <---> OrderRepository
| |ProductService  || <---> ProductRepository
| |FileUploadSvc   || <---> UserService
| +----------------+|
+-------------------+

+-------------------+
| Persistence       |
|                   |
| +----------------+|
| |UserRepository  || <---> User
| |OrderRepository || <---> Order
| |ProductRepo     || <---> Product
| +----------------+|
+-------------------+

+-------------------+
| Domain            |
|                   |
| +----------------+|
| |User            ||
| |Order           ||
| |Product         ||
| +----------------+|
+-------------------+

+-------------------+
| DTO               |
|                   |
| +----------------+|
| |UserDTO         ||
| |OrderDTO        ||
| |ProductDTO      ||
| +----------------+|
+-------------------+
```