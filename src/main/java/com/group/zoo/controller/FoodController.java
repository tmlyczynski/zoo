package com.group.zoo.controller;

import lombok.RequiredArgsConstructor;

import com.group.zoo.domain.entity.Food;
import com.group.zoo.dto.FoodDto;
import com.group.zoo.mapper.FoodMapper;
import com.group.zoo.repository.FoodRepository;
import com.group.zoo.repository.FeedingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodRepository foodRepository;
    private final FoodMapper foodMapper;
    private final FeedingRepository feedingRepository;

    @GetMapping
    public Page<FoodDto> getAll(@RequestParam(required = false) String name, Pageable pageable) {
        Page<Food> foods;
        if (name != null && !name.isEmpty()) {
            foods = foodRepository.findByNameContainingIgnoreCaseAndDeletedFalse(name, pageable);
        } else {
            foods = foodRepository.findAllByDeletedFalse(pageable);
        }
        return foods.map(foodMapper::toDto);
    }

    @GetMapping("/{id}")
    public FoodDto getById(@PathVariable Long id) {
        return foodRepository.findByIdAndDeletedFalse(id)
                .map(foodMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public FoodDto create(@RequestBody FoodDto dto) {
        Food entity = foodMapper.toEntity(dto, feedingRepository);
        return foodMapper.toDto(foodRepository.save(entity));
    }

    @PutMapping("/{id}")
    public FoodDto update(@PathVariable Long id, @RequestBody FoodDto dto) {
        Food entity = foodMapper.toEntity(dto, feedingRepository);
        entity.setId(id);
        return foodMapper.toDto(foodRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        foodRepository.findById(id).ifPresent(food -> {
            food.setDeleted(true);
            foodRepository.save(food);
        });
    }
}
