package com.group.zoo.controller;

import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.dto.EnclosureDto;
import com.group.zoo.mapper.EnclosureMapper;
import com.group.zoo.repository.EnclosureRepository;
import com.group.zoo.repository.ZooRepository;
import com.group.zoo.repository.AnimalRepository;
import com.group.zoo.repository.CleaningTaskRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enclosures")
public class EnclosureController {
    private final EnclosureRepository enclosureRepository;
    private final EnclosureMapper enclosureMapper;
    private final ZooRepository zooRepository;
    private final AnimalRepository animalRepository;
    private final CleaningTaskRepository cleaningTaskRepository;

    public EnclosureController(EnclosureRepository enclosureRepository, EnclosureMapper enclosureMapper, ZooRepository zooRepository, AnimalRepository animalRepository, CleaningTaskRepository cleaningTaskRepository) {
        this.enclosureRepository = enclosureRepository;
        this.enclosureMapper = enclosureMapper;
        this.zooRepository = zooRepository;
        this.animalRepository = animalRepository;
        this.cleaningTaskRepository = cleaningTaskRepository;
    }

    @GetMapping
    public List<EnclosureDto> getAll() {
        return enclosureRepository.findAll().stream()
                .map(enclosureMapper::toDto)
                .collect(Collectors.toList());
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
        enclosureRepository.deleteById(id);
    }
}
