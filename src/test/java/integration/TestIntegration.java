package integration;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import config.IntegrationTestConfig;
import lombok.SneakyThrows;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.service.DataGenerator;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = IntegrationTestConfig.class)
@WebAppConfiguration
public class TestIntegration {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    protected DataGenerator dataGenerator;

    @BeforeEach
    public void setup() throws Exception {

        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

        dataGenerator.createTables();
        dataGenerator.createTestData();
    }

    @Test
    @SneakyThrows
    public void getStudentsShouldReturnModelAttributeAndView() {
        mockMvc.perform(get("/students")).andExpect(view().name("students/index"))
                .andExpect(model().attribute("students", Matchers.hasSize(studentDao.getAllStudents().size())));

    }

    @Test
    @SneakyThrows
    public void getStudentShouldReturnModelAttributeAndView() {
        Student student = studentDao.getAllStudents().get(0);
        mockMvc.perform(get("/students/{id}", student.getId())).andExpect(view().name("students/student"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    @SneakyThrows
    public void createTimeTableShouldReturnModelAttributeAndView() {
        mockMvc.perform(post("/timetables").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("date", LocalDate.now().toString()).param("startTime", "10:00").param("endTime", "10:15")
                .param("group.groupName", "AA-11").param("course.courseName", "Math").param("teacher", "1"))
                .andExpect(view().name("redirect:/timetables")).andExpect(model().attributeExists("timetable"));

    }

    @Test
    @SneakyThrows
    public void updateTimeTableShouldReturnModelAttributeAndView() {
        mockMvc.perform(put("/timetables/1").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("date", LocalDate.now().toString()).param("startTime", "10:00").param("endTime", "10:15")
                .param("group.groupName", "AA-11").param("course.courseName", "Math").param("teacher", "1"))
                .andExpect(view().name("redirect:/timetables")).andExpect(model().attributeExists("timetable"));
    }

    @Test
    @SneakyThrows
    public void deleteTimeTableShouldReturnModelAttributeAndView() {
        mockMvc.perform(delete("/timetables/1")).andExpect(view().name("redirect:/timetables"))
                .andExpect(model().attributeDoesNotExist("timetable"));
    }

}
