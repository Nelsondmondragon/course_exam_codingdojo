package com.codingdojo.repository;

import com.codingdojo.model.UsersCourses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJoinedCourseRepository extends JpaRepository<UsersCourses, Long> {
    UsersCourses findAllByIdUserAndIdCourse(Long idUser, Long idCourse);
}
