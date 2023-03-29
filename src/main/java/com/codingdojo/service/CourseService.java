package com.codingdojo.service;

import com.codingdojo.model.Course;
import com.codingdojo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;

    private final CourseHasUserService courseHasUserService;

    public List<Course> findAll() {
        return this.courseRepository.findAllByOrderByRegisterAsc();
    }

    public List<Course> findAllDesc() {
        return this.courseRepository.findAllByOrderByRegisterDesc();

    }


    public void save(Course course, BindingResult result) {
        if (result.hasErrors()) {
            return;
        }

        if (course.getCapacity() < 1) {
            result.rejectValue("capacity", "Capacity greater that 0", "Capacity greater that 0.");
            return;
        }
        System.out.println("dddddsss");
        this.courseRepository.save(course);
    }

    public Course findById(Long id) {
        return this.courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Task no exists"));
    }


    public void joinCourse(Long id, Long idUser) {
//        Course course = this.findById(id);
//        course.getUsersJoined().add(this.userService.findById(idUser));
//        course.setRegister(course.getUsersJoined().size());
//        this.courseRepository.save(course);

        this.courseHasUserService.save(idUser, id);
    }

    public void update(Long id, Course course, BindingResult result) {
        if (result.hasErrors()) {
            return;
        }
        Course courseRepo = this.findById(id);

        courseRepo.setName(course.getName());
        courseRepo.setCapacity(course.getCapacity());
        courseRepo.setInstructor(course.getInstructor());
        this.courseRepository.save(courseRepo);
    }

    public void delete(Long id) {
        this.courseRepository.deleteById(id);
    }

    public void deleteUser(Long userId, Long id) {
        Course byId = this.findById(id);
        byId.getUsersJoined().remove(this.userService.findById(userId));
        byId.setRegister(byId.getUsersJoined().size());
        this.courseRepository.save(byId);
    }
}
