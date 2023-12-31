package com.elleined.locationapi.mapper;


import com.elleined.locationapi.dto.ProvinceDTO;
import com.elleined.locationapi.model.Province;
import com.elleined.locationapi.model.Region;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class ProvinceMapper {
    @Mappings({
            @Mapping(target = "name", source = "province.locationName"),

            @Mapping(target = "regionId", source = "province.region.id"),
            @Mapping(target = "regionName", source = "province.region.locationName"),

            @Mapping(target = "cityCount", expression = "java(province.getCityCount())"),
            @Mapping(target = "baranggayCount", expression = "java(province.getBaranggayCount())")
    })
    public abstract ProvinceDTO toDTO(Province province);


    @Mappings({
            @Mapping(target = "locationName", source = "provinceDTO.name"),
            @Mapping(target = "cities", expression = "java(new java.util.HashSet<>())"),
            @Mapping(target = "region", expression = "java(region)")
    })
    public abstract Province toEntity(ProvinceDTO provinceDTO,
                                      @Context Region region);
}
