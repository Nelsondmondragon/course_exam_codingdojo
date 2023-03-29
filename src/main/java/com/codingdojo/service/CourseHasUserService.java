package com.codingdojo.service;

import com.codingdojo.model.CourseHasUsers;
import com.codingdojo.repository.CourseHasUserRepository;
import com.codingdojo.repository.CourseRepository;
import com.codingdojo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CourseHasUserService {

    @Autowired
    private CourseHasUserRepository courseHasUserRepository;


    public void save(Long idUser, Long idCourse) {
        CourseHasUsers courseHasUsers = new CourseHasUsers();
        courseHasUsers.setIdCourse(idCourse);
        courseHasUsers.setIdUser(idUser);
        courseHasUserRepository.save(courseHasUsers);
    }

    public List<CourseHasUsers> findAllByCourseIdOrderByCreatedAtDesc(Long idCourse) {
        return this.courseHasUserRepository.findAllByCourseIdOrderByCreatedAtDesc(idCourse);
    }


}
