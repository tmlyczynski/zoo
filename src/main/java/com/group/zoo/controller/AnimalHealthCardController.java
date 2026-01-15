package com.group.zoo.controller;

import lombok.RequiredArgsConstructor;

import com.group.zoo.domain.entity.AnimalHealthCard;
import com.group.zoo.dto.AnimalHealthCardDto;
import com.group.zoo.mapper.AnimalHealthCardMapper;
import com.group.zoo.repository.AnimalHealthCardRepository;
import com.group.zoo.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/animal-health-cards")
public class AnimalHealthCardController {
    private final AnimalHealthCardRepository animalHealthCardRepository;
    private final AnimalHealthCardMapper animalHealthCardMapper;
    private final AnimalRepository animalRepository;

    @GetMapping
    public Page<AnimalHealthCardDto> getAll(@RequestParam(required = false) String veterinarianName, Pageable pageable) {
        Page<AnimalHealthCard> animalHealthCards;
        if (veterinarianName != null && !veterinarianName.isEmpty()) {
            animalHealthCards = animalHealthCardRepository.findByVeterinarianNameContainingIgnoreCase(veterinarianName, pageable);
        } else {
            animalHealthCards = animalHealthCardRepository.findAll(pageable);
        }
        return animalHealthCards.map(animalHealthCardMapper::toDto);
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
        animalHealthCardRepository.findById(id).ifPresent(healthCard -> {
            if (healthCard.getAnimal() != null) {
                throw new IllegalStateException("Cannot delete health card assigned to an animal.");
            }
            animalHealthCardRepository.delete(healthCard);
        });
    }
}
