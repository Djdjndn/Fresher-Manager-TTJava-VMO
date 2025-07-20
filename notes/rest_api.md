# 1. REST là gì?

REST (Representational State Transfer) là một kiểu kiến trúc phần mềm, dùng để xây dựng các dịch vụ web. REST hoạt động dựa trên giao thức HTTP, nơi mỗi URL đại diện cho một "tài nguyên" (resource), và các phương thức HTTP như GET, POST, PUT, DELETE được sử dụng để thao tác với tài nguyên đó.

---
# 2. Đặc điểm của RESTful API

- **Kiến trúc Client-Server**: Tách biệt giữa client và server
- **Không trạng thái (Stateless)**: Mỗi yêu cầu chứa tất cả thông tin cần thiết
- **Khả năng lưu cache**: Phản hồi có thể được lưu cache để cải thiện hiệu suất
- **Giao diện đồng nhất**: Nhận dạng và thao tác tài nguyên nhất quán
- **Hệ thống phân tầng**: Có thể có các server trung gian giữa client và điểm cuối

---
# 3. Các phương thức HTTP thường dùng

- **GET**: Lấy thông tin tài nguyên. /  `GET /users` 
- **POST**: Tạo mới tài nguyên. / `POST /users`   
- **PUT**: Cập nhật toàn bộ tài nguyên. / `PUT /users/1`  
- **PATCH**: Cập nhật một phần tài nguyên. / `PATCH /users/1`     
- **DELETE**: Xoá tài nguyên. / `DELETE /users/1`     

---
# 4. Ví dụ REST API với Spring Boot
```java
@RestController
@RequestMapping("/users")
public class UserController {

    private List<String> users = new ArrayList<>(List.of("Alice", "Bob"));

    @GetMapping
    public List<String> getAllUsers() {
        return users;
    }

    @PostMapping
    public String createUser(@RequestBody String name) {
        users.add(name);
        return "Created user: " + name;
    }

    @DeleteMapping("/{index}")
    public String deleteUser(@PathVariable int index) {
        String removed = users.remove(index);
        return "Deleted user: " + removed;
    }
}


```
# ghi chú các animation quan trọng
| Annotation                              | Ý nghĩa / Vai trò                                                      |
| --------------------------------------- | ---------------------------------------------------------------------- |
| `@RestController`                       | Đánh dấu lớp là controller REST → trả về JSON thay vì HTML             |
| `@RequestMapping`                       | Gắn đường dẫn chung cho controller                                     |
| `@GetMapping`                           | Đánh dấu một method xử lý HTTP GET                                     |
| `@PostMapping`                          | Xử lý HTTP POST request                                                |
| `@DeleteMapping`                        | Xử lý HTTP DELETE request                                              |
| `@PutMapping`                           | Xử lý HTTP PUT request                                                 |
| `@RequestBody`                          | Lấy dữ liệu từ phần body của HTTP request và ánh xạ vào đối tượng Java |
| `@PathVariable`                         | Trích xuất giá trị từ URL                                              |
| `@Autowired`                            | Tiêm phụ thuộc (dependency injection)                                  |
| `@Service`, `@Component`, `@Repository` | Giúp Spring quản lý các class này trong hệ thống (IOC container)       |

| Mã  | Ý nghĩa               |
| --- | --------------------- |
| 200 | OK                    |
| 201 | Created               |
| 204 | No Content            |
| 400 | Bad Request           |
| 401 | Unauthorized          |
| 403 | Forbidden             |
| 404 | Not Found             |
| 500 | Internal Server Error |
