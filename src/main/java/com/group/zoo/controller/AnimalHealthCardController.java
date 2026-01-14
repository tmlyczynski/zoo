package com.group.zoo.controller;

import com.group.zoo.domain.entity.AnimalHealthCard;
import com.group.zoo.dto.AnimalHealthCardDto;
import com.group.zoo.mapper.AnimalHealthCardMapper;
import com.group.zoo.repository.AnimalHealthCardRepository;
import com.group.zoo.repository.AnimalRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animal-health-cards")
public class AnimalHealthCardController {
    private final AnimalHealthCardRepository animalHealthCardRepository;
    private final AnimalHealthCardMapper animalHealthCardMapper;
    private final AnimalRepository animalRepository;

    public AnimalHealthCardController(AnimalHealthCardRepository animalHealthCardRepository, AnimalHealthCardMapper animalHealthCardMapper, AnimalRepository animalRepository) {
        this.animalHealthCardRepository = animalHealthCardRepository;
        this.animalHealthCardMapper = animalHealthCardMapper;
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public List<AnimalHealthCardDto> getAll() {
        return animalHealthCardRepository.findAll().stream()
                .map(animalHealthCardMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AnimalHealthCardDto getById(@PathVariable Long id) {
        return animalHealthCardRepository.findById(id)
                .map(animalHealthCardMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public AnimalHealthCardDto create(@RequestBody AnimalHealthCardDto dto) {
        AnimalHealthCard entity = animalHealthCardMapper.toEntity(dto, animalRepository);
        return animalHealthCardMapper.toDto(animalHealthCardRepository.save(entity));
    }

    @PutMapping("/{id}")
    public AnimalHealthCardDto update(@PathVariable Long id, @RequestBody AnimalHealthCardDto dto) {
        AnimalHealthCard entity = animalHealthCardMapper.toEntity(dto, animalRepository);
        entity.setId(id);
        return animalHealthCardMapper.toDto(animalHealthCardRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animalHealthCardRepository.deleteById(id);
    }
}
