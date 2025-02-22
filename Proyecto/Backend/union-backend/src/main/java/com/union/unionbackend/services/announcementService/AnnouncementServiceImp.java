package com.union.unionbackend.services.announcementService;

import com.union.unionbackend.models.Announcement;
import com.union.unionbackend.models.Course;
import com.union.unionbackend.repositories.AnnouncementRepository;
import com.union.unionbackend.services.courseService.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the AnnouncementService interface. Provides methods to create, retrieve,
 * update, and delete announcements.
 */
@Slf4j
@Service
public class AnnouncementServiceImp implements AnnouncementService {

  private final AnnouncementRepository announcementRepository;
  private final CourseService courseService;

  public AnnouncementServiceImp(AnnouncementRepository announcementRepository,
      CourseService courseService) {
    this.announcementRepository = announcementRepository;
    this.courseService = courseService;
  }

  @Override
  @Transactional
  public Announcement createAnnouncement(Announcement announcement) {
    Course course = courseService.getCourse(announcement.getCourse().getId());
    announcement.setCourse(course);
    return announcementRepository.save(announcement);
  }

  @Override
  public Announcement getAnnouncement(Long id) {
    return announcementRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id " + id));
  }

  @Override
  @Transactional
  public Announcement updateAnnouncement(Long id, Announcement announcement) {
    Course course = courseService.getCourse(announcement.getCourse().getId());
    return announcementRepository.findById(id)
        .map(existingAnnouncement -> {
          existingAnnouncement.setCourse(course);
          existingAnnouncement.setContent(announcement.getContent());
          existingAnnouncement.setTimestamp(Instant.now());
          return announcementRepository.save(existingAnnouncement);
        })
        .orElseThrow(() -> new EntityNotFoundException("Announcement not found with id " + id));
  }

  @Override
  @Transactional
  public boolean deleteAnnouncement(Long id) {
    try {
      if (!announcementRepository.existsById(id)) {
        log.error("Announcement not found with id {}", id);
        return false;
      }
      announcementRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      log.error("Error deleting announcement with id {}: {}", id, e.getMessage());
      return false;
    }
  }

  @Override
  public List<Announcement> getAnnouncementsByCourse(Long courseId) {
    if (!courseService.existsById(courseId)) {
      throw new EntityNotFoundException("Course not found with id " + courseId);
    }
    return announcementRepository.findAllByCourseId(courseId);
  }
}
