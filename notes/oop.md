# 1. OOP là gì?

Lập trình hướng đối tượng (Object-Oriented Programming – OOP) là một mô hình lập trình tổ chức phần mềm xung quanh **đối tượng** – nơi dữ liệu và hành vi của đối tượng được gắn kết với nhau thông qua `class` và `object`.

---

# 2. Các đặc điểm chính của OOP
## 1. Encapsulation – Đóng gói
- Che giấu thông tin nội bộ của object.
- Dữ liệu được bảo vệ thông qua `private` field và truy cập gián tiếp qua `getter/setter`.

## 2.  Inheritance – Kế thừa
- Tái sử dụng code bằng cách cho phép class con kế thừa từ class cha (extends).
- Có thể ghi đè (override) phương thức.

## 3. Polymorphism – Đa hình
- Cho phép gọi cùng một phương thức trên các đối tượng khác nhau.

## 4. Abstraction – Trừu tượng hóa
- Ẩn các chi tiết cài đặt và chỉ cung cấp interface hoặc abstract method cho người dùng.
- Thường dùng với abstract class hoặc interface.

**Ví dụ:**
```java

// 🔒 Encapsulation – Đóng gói
class Person {
    private String name;  // private -> không truy cập trực tiếp từ bên ngoài

    public Person(String name) {
        this.name = name;
    }

    // getter/setter cho phép truy cập an toàn
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// 📦 Abstraction – Trừu tượng hóa
abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    // abstract method -> buộc class con phải implement
    public abstract void speak();
}

// 🧬 Inheritance – Kế thừa
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    // 🔁 Polymorphism – Ghi đè hành vi
    @Override
    public void speak() {
        System.out.println(name + " says: Woof!");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " says: Meow!");
    }
}

// Main chương trình
public class test {
    public static void main(String[] args) {
        // Encapsulation
        Person p = new Person("Alice");
        System.out.println("Person name: " + p.getName());
        p.setName("Bob");
        System.out.println("Updated name: " + p.getName());

        // Inheritance + Abstraction + Polymorphism
        Animal a1 = new Dog("Rex");
        Animal a2 = new Cat("Luna");

        a1.speak();  // Rex says: Woof!
        a2.speak();  // Luna says: Meow!
    }
}




