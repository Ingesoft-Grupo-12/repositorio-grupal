package com.union.unionbackend.services.announcementService;

import com.union.unionbackend.models.Announcement;
import java.util.List;

/**
 * Service interface for managing announcements.
 */
public interface AnnouncementService {

  /**
   * Creates a new announcement.
   *
   * @param announcement the announcement to create
   * @return the created announcement
   */
  Announcement createAnnouncement(Announcement announcement);

  /**
   * Retrieves an announcement by its ID.
   *
   * @param id the ID of the announcement
   * @return the announcement with the specified ID
   */
  Announcement getAnnouncement(Long id);

  /**
   * Updates an existing announcement.
   *
   * @param id           the ID of the announcement to update
   * @param announcement the new data for the announcement
   * @return the updated announcement
   */
  Announcement updateAnnouncement(Long id, Announcement announcement);

  /**
   * Deletes an announcement by its ID.
   *
   * @param id the ID of the announcement to delete
   * @return true if the announcement was successfully deleted, false otherwise
   */
  boolean deleteAnnouncement(Long id);

  /**
   * Retrieves all announcements for a specific course.
   *
   * @param courseId the ID of the course
   * @return a list of announcements associated with the course
   */
  List<Announcement> getAnnouncementsByCourse(Long courseId);
}
