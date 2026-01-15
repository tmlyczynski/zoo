package com.group.zoo.mapper;

import com.group.zoo.domain.entity.Zoo;
import com.group.zoo.dto.ZooDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-15T09:57:08+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260101-2150, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ZooMapperImpl implements ZooMapper {

    @Override
    public ZooDto toDto(Zoo zoo) {
        if ( zoo == null ) {
            return null;
        }

        ZooDto zooDto = new ZooDto();

        zooDto.setEnclosureIds( ZooMapper.enclosureListToIds( zoo.getEnclosures() ) );
        zooDto.setUserIds( ZooMapper.userListToIds( zoo.getUsers() ) );
        zooDto.setCity( zoo.getCity() );
        zooDto.setCountry( zoo.getCountry() );
        zooDto.setId( zoo.getId() );
        zooDto.setName( zoo.getName() );
        zooDto.setOpenFrom( zoo.getOpenFrom() );
        zooDto.setOpenTo( zoo.getOpenTo() );

        return zooDto;
    }

    @Override
    public Zoo toEntity(ZooDto zooDto) {
        if ( zooDto == null ) {
            return null;
        }

        Zoo zoo = new Zoo();

        zoo.setCity( zooDto.getCity() );
        zoo.setCountry( zooDto.getCountry() );
        zoo.setId( zooDto.getId() );
        zoo.setName( zooDto.getName() );
        zoo.setOpenFrom( zooDto.getOpenFrom() );
        zoo.setOpenTo( zooDto.getOpenTo() );

        return zoo;
    }
}
