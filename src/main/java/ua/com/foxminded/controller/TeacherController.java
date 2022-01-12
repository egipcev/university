package ua.com.foxminded.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.TeacherEntity;
import ua.com.foxminded.service.TeacherService;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    @GetMapping()
    public String getTeachers(Model model) {
        try {
            model.addAttribute("teachers", teacherService.getAllTeachers());
        } catch (ServiceException e) {
            log.error("error reading teachers", e);
        }
        return "teachers/index";
    }

    @GetMapping("/{id}")
    public String getTeacher(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("teacher", teacherService.getTeacherById(id));
        } catch (ServiceException e) {
            log.error("error reading teacher", e);
        }
        return "teachers/teacher";
    }

    @GetMapping("/new")
    public String newTeacher(Model model) {
        model.addAttribute("teacher", new TeacherEntity());
        return "teachers/new";
    }

    @PostMapping
    public String create(@ModelAttribute("teacher") TeacherEntity teacher) {
        try {
            teacherService.createTeacher(teacher);
        } catch (ServiceException e) {
            log.error("error creating teacher", e);
        }
        return "redirect:/teachers";
    }

}
