package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Animal;
import com.group.zoo.domain.entity.CleaningTask;
import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.dto.EnclosureDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;
import java.util.List;
import com.group.zoo.repository.ZooRepository;
import com.group.zoo.repository.AnimalRepository;
import com.group.zoo.repository.CleaningTaskRepository;
import com.group.zoo.domain.entity.Zoo;

@Mapper(componentModel = "spring")
public interface EnclosureMapper {

    @Mapping(target = "animalIds", source = "animals", qualifiedByName = "animalListToIds")
    @Mapping(target = "cleaningTaskIds", source = "cleaningTasks", qualifiedByName = "cleaningTaskListToIds")
    @Mapping(target = "zooId", source = "zoo.id")
    EnclosureDto toDto(Enclosure enclosure);

    @Mapping(target = "animals", source = "animalIds", qualifiedByName = "animalIdsToAnimals")
    @Mapping(target = "cleaningTasks", source = "cleaningTaskIds", qualifiedByName = "cleaningTaskIdsToCleaningTasks")
    @Mapping(target = "zoo", source = "zooId", qualifiedByName = "zooIdToZoo")
    Enclosure toEntity(EnclosureDto enclosureDto, @Context ZooRepository zooRepository,
            @Context AnimalRepository animalRepository, @Context CleaningTaskRepository cleaningTaskRepository);

    @Named("animalIdsToAnimals")
    static List<Animal> animalIdsToAnimals(List<Long> animalIds, @Context AnimalRepository animalRepository) {
        if (animalIds == null)
            return null;
        return animalIds.stream().map(id -> animalRepository.findById(id).orElse(null))
                .filter(java.util.Objects::nonNull).toList();
    }

    @Named("cleaningTaskIdsToCleaningTasks")
    static List<CleaningTask> cleaningTaskIdsToCleaningTasks(List<Long> cleaningTaskIds,
            @Context CleaningTaskRepository cleaningTaskRepository) {
        if (cleaningTaskIds == null)
            return null;
        return cleaningTaskIds.stream().map(id -> cleaningTaskRepository.findById(id).orElse(null))
                .filter(java.util.Objects::nonNull).toList();
    }

    @Named("zooIdToZoo")
    static Zoo zooIdToZoo(Long zooId, @Context ZooRepository zooRepository) {
        if (zooId == null)
            return null;
        return zooRepository.findById(zooId).orElse(null);
    }

    @Named("animalListToIds")
    static List<Long> animalListToIds(List<Animal> animals) {
        if (animals == null)
            return null;
        return animals.stream().map(Animal::getId).toList();
    }

    @Named("cleaningTaskListToIds")
    static List<Long> cleaningTaskListToIds(List<CleaningTask> cleaningTasks) {
        if (cleaningTasks == null)
            return null;
        return cleaningTasks.stream().map(CleaningTask::getId).toList();
    }
}
