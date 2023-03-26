package com.codingdojo.repository;


import com.codingdojo.model.Course;
import com.codingdojo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByOrderByRegisterAsc();

    List<Course> findAllByOrderByRegisterDesc();

}
