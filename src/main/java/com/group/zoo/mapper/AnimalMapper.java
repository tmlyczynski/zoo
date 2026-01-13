package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Animal;
import com.group.zoo.dto.AnimalDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Context;
import java.util.List;
import com.group.zoo.repository.EnclosureRepository;
import com.group.zoo.repository.AnimalHealthCardRepository;
import com.group.zoo.domain.entity.Enclosure;
import com.group.zoo.domain.entity.Feeding;
import com.group.zoo.domain.entity.AnimalHealthCard;

@Mapper(componentModel = "spring")
public interface AnimalMapper {


    @Mapping(target = "feedingIds", source = "feedings", qualifiedByName = "feedingListToIds")
    @Mapping(target = "enclosureId", source = "enclosure.id")
    @Mapping(target = "healthCardId", source = "healthCard.id")
    AnimalDto toDto(Animal animal);

    @Mapping(target = "feedings", ignore = true)
    @Mapping(target = "enclosure", source = "enclosureId", qualifiedByName = "enclosureIdToEnclosure")
    @Mapping(target = "healthCard", source = "healthCardId", qualifiedByName = "healthCardIdToHealthCard")
    Animal toEntity(AnimalDto animalDto, @Context EnclosureRepository enclosureRepository, @Context AnimalHealthCardRepository animalHealthCardRepository);


    @Named("enclosureIdToEnclosure")
    static Enclosure enclosureIdToEnclosure(Long enclosureId, @Context EnclosureRepository enclosureRepository) {
        if (enclosureId == null) return null;
        return enclosureRepository.findById(enclosureId).orElse(null);
    }

    @Named("healthCardIdToHealthCard")
    static AnimalHealthCard healthCardIdToHealthCard(Long healthCardId, @Context AnimalHealthCardRepository animalHealthCardRepository) {
        if (healthCardId == null) return null;
        return animalHealthCardRepository.findById(healthCardId).orElse(null);
    }

    @Named("feedingListToIds")
    static List<Long> feedingListToIds(List<Feeding> feedings) {
        if (feedings == null) return null;
        return feedings.stream().map(Feeding::getId).toList();
    }
}
