package com.union.unionbackend.mapper;

import com.union.unionbackend.dtos.AnnouncementDto;
import com.union.unionbackend.models.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnouncementMapper {

  AnnouncementMapper INSTANCE = Mappers.getMapper( AnnouncementMapper.class );

  @Mapping(source = "course.id", target = "courseId")
  AnnouncementDto announcementToDto(Announcement announcement);

  @Mapping(source = "courseId", target = "course.id")
  Announcement dtoToAnnouncement(AnnouncementDto announcementDto);
}