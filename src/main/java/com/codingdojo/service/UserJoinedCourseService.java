package com.codingdojo.service;

import com.codingdojo.model.UsersCourses;
import com.codingdojo.repository.UserJoinedCourseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinedCourseService {

    @Autowired
    private UserJoinedCourseRepository userJoinedCourseRepository;

    public UsersCourses findByIdUserAndIdCourse(Long idUser, Long idCourse) {
        return this.userJoinedCourseRepository.findAllByIdUserAndIdCourse(idUser, idCourse);
    }

}
