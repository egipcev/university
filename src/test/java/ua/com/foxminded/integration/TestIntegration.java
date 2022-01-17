package ua.com.foxminded.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import lombok.SneakyThrows;
import ua.com.foxminded.config.IntegrationTestConfig;
import ua.com.foxminded.controller.dao.Dao;
import ua.com.foxminded.model.entity.StudentEntity;
import ua.com.foxminded.service.DataGenerator;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
@SpringBootTest
//@WebAppConfiguration
@AutoConfigureMockMvc
public class TestIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Dao<StudentEntity> studentDao;

    @Autowired
    protected DataGenerator dataGenerator;

    @BeforeEach
    public void setup() throws Exception {

        dataGenerator.createTables();
        dataGenerator.createTestData();
    }

    @Test
    @SneakyThrows
    public void getStudentsShouldReturnModelAttributeAndView() {
        mockMvc.perform(get("/students")).andExpect(view().name("students/index"))
                .andExpect(model().attribute("students", Matchers.hasSize(studentDao.getAll().size())));

    }

    @Test
    @SneakyThrows
    public void getStudentShouldReturnModelAttributeAndView() {
        StudentEntity student = studentDao.getAll().get(0);
        mockMvc.perform(get("/students/{id}", student.getId())).andExpect(view().name("students/student"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    @SneakyThrows
    public void createTimeTableShouldReturnModelAttributeAndView() {
        mockMvc.perform(post("/timetables").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("date", LocalDate.now().toString()).param("startTime", "10:00").param("endTime", "10:15")
                .param("group.id", "1").param("course.id", "1").param("teacher", "1"))
                .andExpect(view().name("redirect:/timetables")).andExpect(model().attributeExists("timetable"));

    }

    @Test
    @SneakyThrows
    public void updateTimeTableShouldReturnModelAttributeAndView() {
        mockMvc.perform(put("/timetables/1").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("date", LocalDate.now().toString()).param("startTime", "10:00").param("endTime", "10:15")
                .param("group.id", "2").param("course.id", "2").param("teacher", "1"))
                .andExpect(view().name("redirect:/timetables")).andExpect(model().attributeExists("timetable"));
    }

    @Test
    @SneakyThrows
    public void deleteTimeTableShouldReturnModelAttributeAndView() {
        mockMvc.perform(delete("/timetables/1")).andExpect(view().name("redirect:/timetables"))
                .andExpect(model().attributeDoesNotExist("timetable"));
    }

}
