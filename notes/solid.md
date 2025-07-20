# Nguyên lý SOLID trong lập trình hướng đối tượng

**SOLID** là tập hợp 5 nguyên lý thiết kế phần mềm giúp hệ thống dễ bảo trì, mở rộng và ít lỗi hơn. Bao gồm:

---

## 1. Single Responsibility Principle (SRP) – Nguyên lý trách nhiệm đơn
- Một class chỉ nên có một lý do để thay đổi, tức là chỉ đảm nhiệm một chức năng duy nhất.

## 2. OCP – Open/Closed Principle (Nguyên lý mở rộng mà không sửa đổi)
- Mở để mở rộng, đóng để sửa đổi.
- Code nên cho phép mở rộng mà không cần sửa trực tiếp vào class cũ.
- Sử dụng interface, abstract class và kế thừa.

## 3. LSP – Liskov Substitution Principle (Nguyên lý thay thế lớp con)
- Lớp con có thể thay thế lớp cha mà không làm thay đổi tính đúng đắn của chương trình.
- Lớp con phải giữ đúng hành vi của lớp cha.
- Tránh "lừa dối" hệ thống bằng cách override sai mục đích.

## 4.  ISP – Interface Segregation Principle (Nguyên lý phân tách interface)
- Không nên ép class phải implement các phương thức mà nó không dùng.
- Tách interface lớn thành nhiều interface nhỏ hơn.

## 5.  DIP – Dependency Inversion Principle (Nguyên lý đảo ngược phụ thuộc)
- Phụ thuộc vào abstraction, không phụ thuộc vào class cụ thể.
- Class cấp cao không nên phụ thuộc vào class cấp thấp, mà phụ thuộc vào abstraction (interface).