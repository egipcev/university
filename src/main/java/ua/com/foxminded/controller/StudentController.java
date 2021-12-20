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
import ua.com.foxminded.model.Student;
import ua.com.foxminded.service.StudentService;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    @GetMapping()
    public String getStudents(Model model) {
        try {
            model.addAttribute("students", studentService.getAllStudents());
        } catch (ServiceException e) {
            log.error("error reading students", e);
        }
        return "students/index";
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("student", studentService.getStudentById(id));
        } catch (ServiceException e) {
            log.error("error reading student", e);
        }
        return "students/student";
    }

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "students/new";
    }

    @PostMapping
    public String create(@ModelAttribute("student") Student student) {
        try {
            studentService.createStudent(student);
        } catch (ServiceException e) {
            log.error("error creating student", e);
        }
        return "redirect:/students";
    }

}
