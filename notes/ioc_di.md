# 1. IOC – Inversion of Control (Đảo ngược điều khiển)
- IOC là một nguyên lý trong lập trình, trong đó **quyền kiểm soát việc tạo và quản lý các đối tượng không còn do chính class đảm nhiệm**, mà được **trao cho một container hoặc framework** (như Spring). Thay vì class A tạo ra class B bên trong nó, thì A sẽ **nhận B từ bên ngoài**.
- IOC là một nguyên lý lớn, và DI là cách cụ thể để áp dụng nguyên lý đó. Trong các framework như Spring, DI giúp chúng ta viết code sạch hơn, dễ test, dễ mở rộng và ít phụ thuộc lẫn nha

# 2.DI – Dependency Injection (Tiêm phụ thuộc)
- DI là cách hiện thực phổ biến của IOC, trong đó các phụ thuộc (dependencies) của một class sẽ được "tiêm vào" từ bên ngoài, thay vì để class tự tạo ra.
- DI có 3 cách chính:
    + Constructor Injection – tiêm thông qua constructor.
    + Setter Injection – tiêm thông qua setter method.
    + Field Injection – tiêm trực tiếp vào trường (thường dùng với annotation như @Autowired trong Spring).
# 3. IOC Container là gì?
-Trong Spring, IOC Container là thành phần chịu trách nhiệm tạo, cấu hình và quản lý vòng đời của các object (bean). Nó sẽ tiêm các phụ thuộc theo yêu cầu nhờ annotation như @Component, @Autowired, @Service, v.v.

# 4. Lợi ích của IOC/DI
- Tăng tính tái sử dụng và dễ bảo trì.
- Dễ kiểm thử (test) – có thể inject mock hoặc fake class.
- Tách biệt các thành phần → giảm độ phụ thuộc giữa các class.
- Dễ mở rộng hệ thống theo nguyên tắc SOLID.

