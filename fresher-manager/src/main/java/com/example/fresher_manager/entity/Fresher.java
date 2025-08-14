package com.example.fresher_manager.entity;

import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fresher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String programingLanguage;
    private Double score;
    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
    public Fresher(Long id, String name, String email, String programingLanguage, Center center) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.programingLanguage = programingLanguage;
        this.center = center;
    }
    @OneToMany(mappedBy = "fresher", cascade = CascadeType.ALL)
    private List<Score> scores;
    public Double getScore() {
        return score;
    }
    public Long getId() {
        return id;
    }
    public String getName(){
        return name;
    }

    public long getTotalFreshers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalFreshers'");
    }

    public Map<String, Long> getFresherCountByLanguage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFresherCountByLanguage'");
    }

    public Map<String, Long> getFresherCountByCenter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFresherCountByCenter'");
    }

    public String getEmail() {
        return this.email;
    }

    public String getProgramingLanguage() {
        return this.programingLanguage;
    }

    public void setScore(Double aDouble) {
        this.score = aDouble;
    }

  
}
