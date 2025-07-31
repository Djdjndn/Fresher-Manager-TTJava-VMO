package com.example.fresher_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Fresher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String programmingLanguage;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    // Constructor cần thiết cho mapToEntity
    public Fresher(Long id, String name, String email, String programmingLanguage, Center center) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.programmingLanguage = programmingLanguage;
        this.center = center;
    }
}
