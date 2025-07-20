## Stateless vs Stateful

### 1. Stateless (Không trạng thái)

Stateless là mô hình thiết kế trong đó **mỗi request (yêu cầu)** từ client gửi đến server **được xử lý hoàn toàn độc lập** với các request khác. Server **không lưu bất kỳ thông tin nào về trạng thái** hoặc lịch sử của client sau khi request được xử lý xong.

- **Đặc điểm:**
  - Mỗi request phải tự chứa đầy đủ thông tin để server xử lý (ví dụ: token xác thực, dữ liệu yêu cầu).
  - Không cần lưu session hay thông tin người dùng ở phía server.
  - Dễ mở rộng hệ thống vì các server không cần đồng bộ trạng thái với nhau.
  - Thường dùng trong các hệ thống RESTful API, Microservices, Serverless,...

- **Ví dụ:**
  - Một API login yêu cầu bạn gửi `username` và `password` mỗi lần request, không có session lưu trữ trên server.
  - Gửi dữ liệu và token trong mỗi lần gọi API như: `GET /users` kèm theo `Authorization: Bearer <token>`.

---

### 2. Stateful (Có trạng thái)

Stateful là mô hình trong đó **server lưu lại thông tin trạng thái** của client giữa các lần gửi request. Điều này cho phép server biết được “ngữ cảnh” của người dùng, như đã đăng nhập chưa, đang thực hiện đến bước nào trong một tiến trình,...

- **Đặc điểm:**
  - Server lưu session hoặc thông tin trạng thái (state) của client.
  - Client không cần gửi lại toàn bộ thông tin trong mỗi request.
  - Phù hợp với ứng dụng có luồng xử lý phức tạp hoặc liên tục như đăng nhập, giỏ hàng, tiến trình nhiều bước,...
  - Khó mở rộng theo chiều ngang vì cần đồng bộ trạng thái giữa các server hoặc lưu vào Redis/database.

- **Ví dụ:**
  - Khi bạn đăng nhập vào một trang web, server tạo một session ID và lưu lại trạng thái đăng nhập.
  - Trong lần gọi tiếp theo, bạn chỉ cần gửi `session ID`, server sẽ biết bạn là ai và đang ở bước nào.

---

### 3. So sánh 

| Tiêu chí               | Stateless                        | Stateful                        |
|------------------------|----------------------------------|----------------------------------|
| Lưu trạng thái         | Không                            | Có                              |
| Đơn giản mở rộng       | Cao (dễ scale)                   | Thấp hơn (phải đồng bộ session) |
| Dễ triển khai          | Có                               | Phức tạp hơn                    |
| Ứng dụng phổ biến      | REST API, Microservices          | Web App có giỏ hàng, Chat App   |


