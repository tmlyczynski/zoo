package com.group.zoo.mapper;

import com.group.zoo.domain.entity.User;
import com.group.zoo.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;
import java.util.List;
import com.group.zoo.repository.ZooRepository;
import com.group.zoo.repository.FeedingRepository;
import com.group.zoo.repository.CleaningTaskRepository;
import com.group.zoo.domain.entity.Zoo;
import com.group.zoo.domain.entity.Feeding;
import com.group.zoo.domain.entity.CleaningTask;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "feedingIds", source = "feedings", qualifiedByName = "feedingListToIds")
    @Mapping(target = "cleaningTaskIds", source = "cleanings", qualifiedByName = "cleaningTaskListToIds")
    @Mapping(target = "zooId", source = "zoo.id")
    UserDto toDto(User user);

    @Mapping(target = "feedings", source = "feedingIds", qualifiedByName = "feedingIdsToFeedings")
    @Mapping(target = "cleanings", source = "cleaningTaskIds", qualifiedByName = "cleaningTaskIdsToCleaningTasks")
    @Mapping(target = "zoo", source = "zooId", qualifiedByName = "zooIdToZoo")
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDto userDto, @Context ZooRepository zooRepository, @Context FeedingRepository feedingRepository,
            @Context CleaningTaskRepository cleaningTaskRepository);

    @Named("feedingIdsToFeedings")
    static List<Feeding> feedingIdsToFeedings(List<Long> feedingIds, @Context FeedingRepository feedingRepository) {
        if (feedingIds == null)
            return null;
        return feedingIds.stream().map(id -> feedingRepository.findById(id).orElse(null))
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

    @Named("feedingListToIds")
    static List<Long> feedingListToIds(List<Feeding> feedings) {
        if (feedings == null)
            return null;
        return feedings.stream().map(Feeding::getId).toList();
    }

    @Named("cleaningTaskListToIds")
    static List<Long> cleaningTaskListToIds(List<CleaningTask> cleaningTasks) {
        if (cleaningTasks == null)
            return null;
        return cleaningTasks.stream().map(CleaningTask::getId).toList();
    }
}
