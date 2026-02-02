package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Animal;
import com.group.zoo.domain.entity.AnimalHealthCard;
import com.group.zoo.dto.AnimalHealthCardDto;
import com.group.zoo.repository.AnimalRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-02T09:10:33+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260128-0750, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class AnimalHealthCardMapperImpl implements AnimalHealthCardMapper {

    @Override
    public AnimalHealthCardDto toDto(AnimalHealthCard animalHealthCard) {
        if ( animalHealthCard == null ) {
            return null;
        }

        AnimalHealthCardDto animalHealthCardDto = new AnimalHealthCardDto();

        animalHealthCardDto.setAnimalId( animalHealthCardAnimalId( animalHealthCard ) );
        animalHealthCardDto.setId( animalHealthCard.getId() );
        animalHealthCardDto.setLastCheckupDate( animalHealthCard.getLastCheckupDate() );
        animalHealthCardDto.setVeterinarianName( animalHealthCard.getVeterinarianName() );

        return animalHealthCardDto;
    }

    @Override
    public AnimalHealthCard toEntity(AnimalHealthCardDto animalHealthCardDto, AnimalRepository animalRepository) {
        if ( animalHealthCardDto == null ) {
            return null;
        }

        AnimalHealthCard animalHealthCard = new AnimalHealthCard();

        animalHealthCard.setAnimal( AnimalHealthCardMapper.animalIdToAnimal( animalHealthCardDto.getAnimalId(), animalRepository ) );
        animalHealthCard.setId( animalHealthCardDto.getId() );
        animalHealthCard.setLastCheckupDate( animalHealthCardDto.getLastCheckupDate() );
        animalHealthCard.setVeterinarianName( animalHealthCardDto.getVeterinarianName() );

        return animalHealthCard;
    }

    private Long animalHealthCardAnimalId(AnimalHealthCard animalHealthCard) {
        if ( animalHealthCard == null ) {
            return null;
        }
        Animal animal = animalHealthCard.getAnimal();
        if ( animal == null ) {
            return null;
        }
        Long id = animal.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
