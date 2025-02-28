package com.union.unionbackend.services.announcementService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.union.unionbackend.models.Announcement;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.repositories.AnnouncementRepository;
import com.union.unionbackend.services.courseService.CourseService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AnnouncementServiceImpTest {

  @Mock
  private AnnouncementRepository announcementRepository;

  @Mock
  private CourseService courseService;

  @InjectMocks
  private AnnouncementServiceImp announcementService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAnnouncementsByCourse() {
    Long courseId = 1L;
    Course course = new Course();
    course.setId(courseId);

    Announcement announcement1 = new Announcement();
    announcement1.setId(1L);
    announcement1.setCourse(course);

    Announcement announcement2 = new Announcement();
    announcement2.setId(2L);
    announcement2.setCourse(course);

    List<Announcement> announcements = Arrays.asList(announcement1, announcement2);

    when(courseService.existsById(courseId)).thenReturn(true);
    when(announcementRepository.findAllByCourseId(courseId)).thenReturn(announcements);

    List<Announcement> result = announcementService.getAnnouncementsByCourse(courseId);

    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(announcement1, result.get(0));
    assertEquals(announcement2, result.get(1));

    verify(courseService, times(1)).existsById(courseId);
    verify(announcementRepository, times(1)).findAllByCourseId(courseId);
  }

  @Test
  void getAnnouncementsByCourse_CourseNotFound() {
    Long courseId = 1L;

    when(courseService.existsById(courseId)).thenReturn(false);

    assertThrows(EntityNotFoundException.class, () -> {
      announcementService.getAnnouncementsByCourse(courseId);
    });

    verify(courseService, times(1)).existsById(courseId);
    verify(announcementRepository, times(0)).findAllByCourseId(courseId);
  }
}