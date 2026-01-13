package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Feeding;
import com.group.zoo.dto.FeedingDto;
import com.group.zoo.domain.entity.Animal;
import com.group.zoo.domain.entity.User;
import com.group.zoo.domain.entity.Food;
import com.group.zoo.repository.AnimalRepository;
import com.group.zoo.repository.UserRepository;
import com.group.zoo.repository.FoodRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;

@Mapper(componentModel = "spring")
public interface FeedingMapper {

    @Mapping(target = "animalId", source = "animal.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "foodId", source = "food.id")
    FeedingDto toDto(Feeding feeding);

    @Mapping(target = "animal", source = "animalId", qualifiedByName = "animalIdToAnimal")
    @Mapping(target = "user", source = "userId", qualifiedByName = "userIdToUser")
    @Mapping(target = "food", source = "foodId", qualifiedByName = "foodIdToFood")
    Feeding toEntity(FeedingDto feedingDto, @Context AnimalRepository animalRepository, @Context UserRepository userRepository, @Context FoodRepository foodRepository);

    @Named("animalIdToAnimal")
    static Animal animalIdToAnimal(Long animalId, @Context AnimalRepository animalRepository) {
        if (animalId == null) return null;
        return animalRepository.findById(animalId).orElse(null);
    }

    @Named("userIdToUser")
    static User userIdToUser(Long userId, @Context UserRepository userRepository) {
        if (userId == null) return null;
        return userRepository.findById(userId).orElse(null);
    }

    @Named("foodIdToFood")
    static Food foodIdToFood(Long foodId, @Context FoodRepository foodRepository) {
        if (foodId == null) return null;
        return foodRepository.findById(foodId).orElse(null);
    }
}
