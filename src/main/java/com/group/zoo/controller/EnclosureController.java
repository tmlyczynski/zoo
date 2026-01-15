package com.group.zoo.controller;

import lombok.RequiredArgsConstructor;

import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.domain.enums.EnclosureType;
import com.group.zoo.dto.EnclosureDto;
import com.group.zoo.mapper.EnclosureMapper;
import com.group.zoo.repository.EnclosureRepository;
import com.group.zoo.repository.ZooRepository;
import com.group.zoo.repository.AnimalRepository;
import com.group.zoo.repository.CleaningTaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/enclosures")
public class EnclosureController {
    private final EnclosureRepository enclosureRepository;
    private final EnclosureMapper enclosureMapper;
    private final ZooRepository zooRepository;
    private final AnimalRepository animalRepository;
    private final CleaningTaskRepository cleaningTaskRepository;

    @GetMapping
    public Page<EnclosureDto> getAll(@RequestParam(required = false) EnclosureType type, Pageable pageable) {
        Page<Enclosure> enclosures;
        if (type != null) {
            enclosures = enclosureRepository.findByType(type, pageable);
        } else {
            enclosures = enclosureRepository.findAll(pageable);
        }
        return enclosures.map(enclosureMapper::toDto);
    }

    @GetMapping("/{id}")
    public EnclosureDto getById(@PathVariable Long id) {
        return enclosureRepository.findById(id)
                .map(enclosureMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public EnclosureDto create(@RequestBody EnclosureDto dto) {
        Enclosure entity = enclosureMapper.toEntity(dto, zooRepository, animalRepository, cleaningTaskRepository);
        return enclosureMapper.toDto(enclosureRepository.save(entity));
    }

    @PutMapping("/{id}")
    public EnclosureDto update(@PathVariable Long id, @RequestBody EnclosureDto dto) {
        Enclosure entity = enclosureMapper.toEntity(dto, zooRepository, animalRepository, cleaningTaskRepository);
        entity.setId(id);
        return enclosureMapper.toDto(enclosureRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        enclosureRepository.findById(id).ifPresent(enclosure -> {
            if (enclosure.getAnimals() == null || enclosure.getAnimals().isEmpty()) {
                enclosureRepository.delete(enclosure);
            } else {
                throw new IllegalStateException("Cannot delete enclosure with animals inside.");
            }
        });
    }
}
