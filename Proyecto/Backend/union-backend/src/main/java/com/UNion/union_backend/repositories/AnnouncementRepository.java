package com.UNion.union_backend.repositories;

import com.UNion.union_backend.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
