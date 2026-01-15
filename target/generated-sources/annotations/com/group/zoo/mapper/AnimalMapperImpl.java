package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Animal;
import com.group.zoo.domain.entity.AnimalHealthCard;
import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.domain.enums.AnimalType;
import com.group.zoo.domain.enums.DietType;
import com.group.zoo.dto.AnimalDto;
import com.group.zoo.repository.AnimalHealthCardRepository;
import com.group.zoo.repository.EnclosureRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T10:47:53+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class AnimalMapperImpl implements AnimalMapper {

    @Override
    public AnimalDto toDto(Animal animal) {
        if ( animal == null ) {
            return null;
        }

        AnimalDto animalDto = new AnimalDto();

        animalDto.setFeedingIds( AnimalMapper.feedingListToIds( animal.getFeedings() ) );
        animalDto.setEnclosureId( animalEnclosureId( animal ) );
        animalDto.setHealthCardId( animalHealthCardId( animal ) );
        animalDto.setId( animal.getId() );
        animalDto.setName( animal.getName() );
        animalDto.setSpecies( animal.getSpecies() );
        animalDto.setDateOfBirth( animal.getDateOfBirth() );
        if ( animal.getType() != null ) {
            animalDto.setType( animal.getType().name() );
        }
        if ( animal.getDiet() != null ) {
            animalDto.setDiet( animal.getDiet().name() );
        }

        return animalDto;
    }

    @Override
    public Animal toEntity(AnimalDto animalDto, EnclosureRepository enclosureRepository, AnimalHealthCardRepository animalHealthCardRepository) {
        if ( animalDto == null ) {
            return null;
        }

        Animal animal = new Animal();

        animal.setEnclosure( AnimalMapper.enclosureIdToEnclosure( animalDto.getEnclosureId(), enclosureRepository ) );
        animal.setHealthCard( AnimalMapper.healthCardIdToHealthCard( animalDto.getHealthCardId(), animalHealthCardRepository ) );
        animal.setId( animalDto.getId() );
        animal.setName( animalDto.getName() );
        animal.setSpecies( animalDto.getSpecies() );
        animal.setDateOfBirth( animalDto.getDateOfBirth() );
        if ( animalDto.getType() != null ) {
            animal.setType( Enum.valueOf( AnimalType.class, animalDto.getType() ) );
        }
        if ( animalDto.getDiet() != null ) {
            animal.setDiet( Enum.valueOf( DietType.class, animalDto.getDiet() ) );
        }

        return animal;
    }

    private Long animalEnclosureId(Animal animal) {
        if ( animal == null ) {
            return null;
        }
        Enclosure enclosure = animal.getEnclosure();
        if ( enclosure == null ) {
            return null;
        }
        Long id = enclosure.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long animalHealthCardId(Animal animal) {
        if ( animal == null ) {
            return null;
        }
        AnimalHealthCard healthCard = animal.getHealthCard();
        if ( healthCard == null ) {
            return null;
        }
        Long id = healthCard.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
