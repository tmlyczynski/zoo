package com.group.zoo.domain.entity;

import java.util.List;

import com.group.zoo.domain.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String login;
    private String password;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;

    @OneToMany(mappedBy = "user")
    private List<Feeding> feedings;

    @OneToMany(mappedBy = "user")
    private List<CleaningTask> cleanings;

    private boolean deleted = false;
}
