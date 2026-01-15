package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.domain.entity.Zoo;
import com.group.zoo.domain.enums.EnclosureType;
import com.group.zoo.dto.EnclosureDto;
import com.group.zoo.repository.AnimalRepository;
import com.group.zoo.repository.CleaningTaskRepository;
import com.group.zoo.repository.ZooRepository;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T11:03:38+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class EnclosureMapperImpl implements EnclosureMapper {

    @Override
    public EnclosureDto toDto(Enclosure enclosure) {
        if ( enclosure == null ) {
            return null;
        }

        EnclosureDto enclosureDto = new EnclosureDto();

        enclosureDto.setAnimalIds( EnclosureMapper.animalListToIds( enclosure.getAnimals() ) );
        enclosureDto.setCleaningTaskIds( EnclosureMapper.cleaningTaskListToIds( enclosure.getCleaningTasks() ) );
        enclosureDto.setZooId( enclosureZooId( enclosure ) );
        enclosureDto.setId( enclosure.getId() );
        enclosureDto.setName( enclosure.getName() );
        if ( enclosure.getType() != null ) {
            enclosureDto.setType( enclosure.getType().name() );
        }

        return enclosureDto;
    }

    @Override
    public Enclosure toEntity(EnclosureDto enclosureDto, ZooRepository zooRepository, AnimalRepository animalRepository, CleaningTaskRepository cleaningTaskRepository) {
        if ( enclosureDto == null ) {
            return null;
        }

        Enclosure enclosure = new Enclosure();

        enclosure.setAnimals( EnclosureMapper.animalIdsToAnimals( enclosureDto.getAnimalIds(), animalRepository ) );
        enclosure.setCleaningTasks( EnclosureMapper.cleaningTaskIdsToCleaningTasks( enclosureDto.getCleaningTaskIds(), cleaningTaskRepository ) );
        enclosure.setZoo( EnclosureMapper.zooIdToZoo( enclosureDto.getZooId(), zooRepository ) );
        enclosure.setId( enclosureDto.getId() );
        enclosure.setName( enclosureDto.getName() );
        if ( enclosureDto.getType() != null ) {
            enclosure.setType( Enum.valueOf( EnclosureType.class, enclosureDto.getType() ) );
        }

        return enclosure;
    }

    private Long enclosureZooId(Enclosure enclosure) {
        if ( enclosure == null ) {
            return null;
        }
        Zoo zoo = enclosure.getZoo();
        if ( zoo == null ) {
            return null;
        }
        Long id = zoo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
