package com.group.zoo.controller;

import com.group.zoo.domain.entity.Animal;
import com.group.zoo.dto.AnimalDto;
import com.group.zoo.mapper.AnimalMapper;
import com.group.zoo.repository.AnimalRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public AnimalController(AnimalRepository animalRepository, AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.animalMapper = animalMapper;
    }

    @GetMapping
    public List<AnimalDto> getAll() {
        return animalRepository.findAll().stream()
                .map(animalMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AnimalDto getById(@PathVariable Long id) {
        return animalRepository.findById(id)
                .map(animalMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public AnimalDto create(@RequestBody AnimalDto dto) {
        Animal entity = animalMapper.toEntity(dto, null, null);
        return animalMapper.toDto(animalRepository.save(entity));
    }

    @PutMapping("/{id}")
    public AnimalDto update(@PathVariable Long id, @RequestBody AnimalDto dto) {
        Animal entity = animalMapper.toEntity(dto, null, null);
        entity.setId(id);
        return animalMapper.toDto(animalRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animalRepository.deleteById(id);
    }
}
