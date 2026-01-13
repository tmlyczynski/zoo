package com.group.zoo.domain.entity;

import java.util.List;

import com.group.zoo.domain.enums.DietType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private DietType dietType;
    private double caloriesPerServing;

    @OneToMany(mappedBy = "food")
    private List<Feeding> feedings;
}
