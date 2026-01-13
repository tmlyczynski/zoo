package com.group.zoo.domain.entity;

import java.time.LocalDate;
import java.util.List;

import com.group.zoo.domain.enums.AnimalType;
import com.group.zoo.domain.enums.DietType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private AnimalType type;

    @Enumerated(EnumType.STRING)
    private DietType diet;

    @ManyToOne
    @JoinColumn(name = "enclosure_id")
    private Enclosure enclosure;

    @OneToOne
    @JoinColumn(name = "health_card_id")
    private AnimalHealthCard healthCard;

    @OneToMany(mappedBy = "animal")
    private List<Feeding> feedings;
}
