package com.example.fresher_manager.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(mappedBy = "fresher", cascade = CascadeType.ALL)
    private Score score;

    @OneToMany(mappedBy = "fresher")
    private List<Assignment> assignments;
}
