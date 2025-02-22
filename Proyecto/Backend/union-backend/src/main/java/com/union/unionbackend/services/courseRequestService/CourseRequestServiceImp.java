package com.union.unionbackend.services.courseRequestService;

import com.union.unionbackend.models.CourseRequest;

import java.util.List;
import java.util.Optional;

public class CourseRequestServiceImp implements CourseRequestService {
    @Override
    public boolean sendInvitation(String email, Long courseId) {
        return false;
    }

    @Override
    public Optional<CourseRequest> acceptInvitation(String email, Long courseId) {
        return Optional.empty();
    }

    @Override
    public Optional<CourseRequest> rejectInvitation(String email, Long courseId) {
        return Optional.empty();
    }

    @Override
    public List<CourseRequest> getPendingRequests(Long courseId) {
        return List.of();
    }

    @Override
    public List<CourseRequest> getRequestsByStudent(Long studentId) {
        return List.of();
    }
}
