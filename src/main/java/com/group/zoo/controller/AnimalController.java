package com.group.zoo.controller;

import lombok.RequiredArgsConstructor;

import com.group.zoo.domain.entity.Animal;
import com.group.zoo.dto.AnimalDto;
import com.group.zoo.mapper.AnimalMapper;
import com.group.zoo.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    @GetMapping
    public Page<AnimalDto> getAll(@RequestParam(required = false) String name, Pageable pageable) {
        Page<Animal> animals;
        if (name != null && !name.isEmpty()) {
            animals = animalRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            animals = animalRepository.findAll(pageable);
        }
        return animals.map(animalMapper::toDto);
  
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
