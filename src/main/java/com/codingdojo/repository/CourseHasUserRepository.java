package com.codingdojo.repository;

import com.codingdojo.model.CourseHasUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseHasUserRepository extends JpaRepository<CourseHasUsers, Long> {
    List<CourseHasUsers> findAllByCourseIdOrderByCreatedAtDesc(Long idCourse);

}
