package ua.com.foxminded.controller;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.dto.CourseDto;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.dto.TimeTableItemDto;
import ua.com.foxminded.model.entity.TimeTableItemEntity;
import ua.com.foxminded.service.CourseService;
import ua.com.foxminded.service.GroupService;
import ua.com.foxminded.service.TeacherService;
import ua.com.foxminded.service.TimeTableService;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/timetables")
public class TimeTableController {

    private TimeTableService timeTableService;
    private TeacherService teacherService;
    private CourseService courseService;
    private GroupService groupService;
    private ModelMapper modelMapper;

    @GetMapping()
    public String getTimeTables(Model model) {
        try {
            model.addAttribute("timetables", timeTableService.getAllTimeTableItems());
        } catch (ServiceException e) {
            log.error("error reading timetables", e);
        }
        return "timetables/index";
    }

    @GetMapping("/new")
    public String newTimeTable(Model model) {
        model.addAttribute("timetable", new TimeTableItemDto());
        try {
            model.addAttribute("teachers",
                    teacherService.getAllTeachers().stream()
                            .map(teacherEntity -> modelMapper.map(teacherEntity, TeacherDto.class))
                            .collect(Collectors.toList()));
            model.addAttribute("courses", courseService.getAllCourses().stream()
                    .map(courseEntity -> modelMapper.map(courseEntity, CourseDto.class)).collect(Collectors.toList()));
            model.addAttribute("groups", groupService.getAllGroups().stream()
                    .map(groupEntity -> modelMapper.map(groupEntity, GroupDto.class)).collect(Collectors.toList()));
        } catch (ServiceException e) {
            log.error("error reading teachers/courses/groups", e);
        }
        return "timetables/new";
    }

    @PostMapping
    public String create(@ModelAttribute("timetable") TimeTableItemDto timeTableItem) {
        try {
            timeTableService.createTimeTableRecord(modelMapper.map(timeTableItem, TimeTableItemEntity.class));
        } catch (ServiceException e) {
            log.error("error creating time table", e);
        }
        return "redirect:/timetables";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        try {
            model.addAttribute("timetable", timeTableService.getTimeTableItemById(id));
        } catch (ServiceException e) {
            log.error("error reading time table", e);
        }
        try {
            model.addAttribute("teachers",
                    teacherService.getAllTeachers().stream()
                            .map(teacherEntity -> modelMapper.map(teacherEntity, TeacherDto.class))
                            .collect(Collectors.toList()));
            model.addAttribute("courses", courseService.getAllCourses().stream()
                    .map(courseEntity -> modelMapper.map(courseEntity, CourseDto.class)).collect(Collectors.toList()));
            model.addAttribute("groups", groupService.getAllGroups().stream()
                    .map(groupEntity -> modelMapper.map(groupEntity, GroupDto.class)).collect(Collectors.toList()));
        } catch (ServiceException e) {
            log.error("error reading teachers/courses/groups", e);
        }
        return "timetables/edit";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("timetable") TimeTableItemDto timeTableItem) {
        try {
            timeTableService.updateTimeTableRecord(modelMapper.map(timeTableItem, TimeTableItemEntity.class));
        } catch (ServiceException e) {
            log.error("error updating time table", e);
        }
        return "redirect:/timetables";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        try {
            timeTableService.deleteTimeTableRecord(id);
        } catch (ServiceException e) {
            log.error("error deleting time table", e);
        }
        return "redirect:/timetables";
    }

}
