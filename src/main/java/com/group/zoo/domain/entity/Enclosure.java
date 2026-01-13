package com.group.zoo.domain.entity;

import java.util.List;

import com.group.zoo.domain.enums.EnclosureType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Enclosure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private EnclosureType type;

    @ManyToOne
    @JoinColumn(name = "zoo_id")
    private Zoo zoo;

    @OneToMany(mappedBy = "enclosure", cascade = CascadeType.ALL)
    private List<Animal> animals;

    @OneToMany(mappedBy = "enclosure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CleaningTask> cleaningTasks;
}
