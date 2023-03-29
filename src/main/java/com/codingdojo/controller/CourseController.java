package com.codingdojo.controller;


import com.codingdojo.model.Course;
import com.codingdojo.model.CourseHasUsers;
import com.codingdojo.service.CourseHasUserService;
import com.codingdojo.service.CourseService;
import com.codingdojo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CourseController {

    public final CourseService courseService;
    public final UserService userService;

    public final CourseHasUserService service;

    public final CourseHasUserService courseHasUserService;

    @GetMapping(value = {"/courses"})
    public String dashboard(@RequestParam(value = "order", required = false) String order, Model model, HttpSession session) {
        Long idUser = (Long) session.getAttribute("userLoginId");
        if (idUser == null) {
            return "redirect:/";
        }
        session.setAttribute("user", this.userService.findById(idUser));

        if (order == null) {
            model.addAttribute("courses", this.courseService.findAll());
        } else if (order.equals("desc")) {
            model.addAttribute("courses", this.courseService.findAllDesc());
        }

        return "dashboard.jsp";
    }


    @GetMapping("/courses/new")
    public String viewNewCourse(Model model, HttpSession session) {
        Long idUser = (Long) session.getAttribute("userLoginId");
        if (idUser == null) {
            return "redirect:/";
        }
        model.addAttribute("course", new Course());

        return "new.jsp";
    }

    @GetMapping("/courses/join/{id}")
    public String joinCourse(@PathVariable Long id, HttpSession session) {
        Long idUser = (Long) session.getAttribute("userLoginId");
        if (idUser == null) {
            return "redirect:/";
        }
        this.courseService.joinCourse(id, idUser);
        return "redirect:/courses";
    }


    @PostMapping("/courses/new")
    public String newCourse(@Valid @ModelAttribute("course") Course course, BindingResult result, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userLoginId");
        System.out.println("dddd");
        if (userId == null) {
            return "redirect:/";
        }
        this.courseService.save(course, result);
        if (result.hasErrors()) {
            return "new.jsp";
        }
        return "redirect:/courses";
    }

    @GetMapping("/courses/{id}")
    public String viewCourse(@PathVariable Long id, @RequestParam(value = "order", required = false) String order, Model model, HttpSession session) {
        Long idUser = (Long) session.getAttribute("userLoginId");
        if (idUser == null) {
            return "redirect:/";
        }

        Course course = this.courseService.findById(id);
        model.addAttribute("course", course);

        if (order == null) {
            model.addAttribute("courseHasUsers",
                    course.getCourseHasUsers());
        } else if (order.equals("desc")) {
            model.addAttribute("courseHasUsers",
                    this.courseHasUserService.findAllByCourseIdOrderByCreatedAtDesc(course.getId()));

        }

        return "view.jsp";
    }


    @GetMapping("/courses/{id}/edit")
    public String editCourse(@PathVariable Long id, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userLoginId");

        Course byId = this.courseService.findById(id);

        if (userId == null) {
            return "redirect:/";
        }

        model.addAttribute("course", byId);
        return "edit.jsp";
    }

    @PostMapping("/courses/{id}/edit")
    public String editCourse(@PathVariable Long id, @Valid @ModelAttribute("course") Course course,
                             BindingResult result
            , HttpSession session) {

        Long userId = (Long) session.getAttribute("userLoginId");

        if (userId == null) {
            return "redirect:/";
        }
        this.courseService.update(id, course, result);
        if (result.hasErrors()) {
            return "edit.jsp";
        }

        return "redirect:/courses";
    }

    //
    @DeleteMapping("/course/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        this.courseService.delete(id);
        return "redirect:/courses";
    }


    @GetMapping("/courses/deleteuser/{id}")
    public String deleteJoinUser(@PathVariable("id") Long id, HttpSession session) {

        Long userId = (Long) session.getAttribute("userLoginId");
        if (userId == null) {
            return "redirect:/";
        }
        System.out.println("ooo");
        this.courseService.deleteUser(userId, id);
        return "redirect:/courses";
    }
}
