package com.group.zoo.mapper;

import com.group.zoo.domain.entity.AnimalHealthCard;
import com.group.zoo.dto.AnimalHealthCardDto;
import com.group.zoo.domain.entity.Animal;
import com.group.zoo.repository.AnimalRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;

@Mapper(componentModel = "spring")
public interface AnimalHealthCardMapper {

    @Mapping(target = "animalId", source = "animal.id")
    AnimalHealthCardDto toDto(AnimalHealthCard animalHealthCard);

    @Mapping(target = "animal", source = "animalId", qualifiedByName = "animalIdToAnimal")
    AnimalHealthCard toEntity(AnimalHealthCardDto animalHealthCardDto, @Context AnimalRepository animalRepository);

    @Named("animalIdToAnimal")
    static Animal animalIdToAnimal(Long animalId, @Context AnimalRepository animalRepository) {
        if (animalId == null) return null;
        return animalRepository.findById(animalId).orElse(null);
    }
}
