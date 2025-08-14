package com.example.fresher_manager.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String manager;
    private LocalDate startDate;
    private LocalDate endDate;
    private String programmingLanguage;
    private String status; // ví dụ: not_start, ongoing, canceled, closed

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @ManyToMany
    @JoinTable(
        name = "project_fresher",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "fresher_id")
    )
    private Set<Fresher> freshers = new HashSet<>();
    public Set<Fresher> getFreshers() {
        return freshers;
    }

    public void setFreshers(Set<Fresher> freshers) {
        this.freshers = freshers;
    }


    public long getTotalProjects() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalProjects'");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager; 
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public String getStatus() {
        return status;
    }
}
