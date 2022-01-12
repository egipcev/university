package ua.com.foxminded.controller;

import org.modelmapper.ModelMapper;
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
import ua.com.foxminded.model.dto.CourseDto;
import ua.com.foxminded.model.entity.CourseEntity;
import ua.com.foxminded.service.CourseService;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;
    private ModelMapper modelMapper;

    @GetMapping()
    public String getCourses(Model model) {
        try {
            model.addAttribute("courses", courseService.getAllCourses());
        } catch (ServiceException e) {
            log.error("error reading courses", e);
        }
        return "courses/index";
    }

    @GetMapping("/{id}")
    public String getCourse(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("course", courseService.getCourseById(id));
        } catch (ServiceException e) {
            log.error("error reading course", e);
        }
        return "courses/course";
    }

    @GetMapping("/new")
    public String newCourse(Model model) {
        model.addAttribute("course", new CourseDto());
        return "courses/new";
    }

    @PostMapping
    public String create(@ModelAttribute("course") CourseDto course) {
        try {
            courseService.createCourse(modelMapper.map(course, CourseEntity.class));
        } catch (ServiceException e) {
            log.error("error creating course", e);
        }
        return "redirect:/courses";
    }

}
