package ua.com.foxminded.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import ua.com.foxminded.controller.dao.Dao;
import ua.com.foxminded.controller.exception.ServiceException;
import ua.com.foxminded.model.entity.CourseEntity;
import ua.com.foxminded.model.entity.GroupEntity;
import ua.com.foxminded.model.entity.StudentEntity;
import ua.com.foxminded.model.entity.TeacherEntity;
import ua.com.foxminded.model.entity.TimeTableItemEntity;

@Service
public class DataGenerator {

    private static final int COURSE_NAME_INDEX = 0;
    private static final int COURSE_DESCRIPTION_INDEX = 1;
    private static final int GROUP_NAME_INDEX = 0;
    private static final String SEMICOLON = ";";
    private static final int STUDENT_GROUP_INDEX = 0;
    private static final int STUDENT_FIRST_NAME_INDEX = 1;
    private static final int STUDENT_LAST_NAME_INDEX = 2;
    private static final int TEACHER_FIRST_NAME_INDEX = 0;
    private static final int TEACHER_LAST_NAME_INDEX = 1;
    private static final int TIME_TABLE_DATE_INDEX = 0;
    private static final int TIME_TABLE_START_TIME_INDEX = 1;
    private static final int TIME_TABLE_END_TIME_INDEX = 2;
    private static final int TIME_TABLE_COURSE_NAME_INDEX = 3;
    private static final int TIME_TABLE_GROUP_NAME_INDEX = 4;
    private static final int TIME_TABLE_TEACHER_FIRST_NAME_INDEX = 5;
    private static final int TIME_TABLE_TEACHER_LAST_NAME_INDEX = 6;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Dao<StudentEntity> studentDao;

    @Autowired
    private Dao<GroupEntity> groupDao;

    @Autowired
    private Dao<CourseEntity> courseDao;

    @Autowired
    private Dao<TeacherEntity> teacherDao;

    @Autowired
    private Dao<TimeTableItemEntity> timeTableItemDao;

    @Value("classpath:CREATE_TABLES.sql")
    private Resource createTables;

    @Value("classpath:groups.txt")
    private Resource groups;

    @Value("classpath:courses.txt")
    private Resource courses;

    @Value("classpath:students.txt")
    private Resource students;

    @Value("classpath:teachers.txt")
    private Resource teachers;

    @Value("classpath:time_tables.txt")
    private Resource timeTables;

    public void createTables() throws ServiceException {
        try {

            ScriptUtils.executeSqlScript(dataSource.getConnection(), createTables);
        } catch (SQLException e) {
            throw new ServiceException("error while creating tables", e);
        }
    }

    public void createTestData() throws ServiceException {
        createTestGroups();
        createTestCourses();
        createTestStudents();
        createTestTeachers();
        createTestTimeTables();
    }

    private void createTestGroups() throws ServiceException {
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(groups.getURI())).lines()) {

            groupDao.saveAll(lines.map(line -> line.split(SEMICOLON)).map(array -> {
                GroupEntity group = new GroupEntity();
                group.setGroupName(array[GROUP_NAME_INDEX]);
                return group;
            }).collect(Collectors.toList()));

        } catch (IOException e) {
            throw new ServiceException("error while creating test groups", e);
        }

    }

    private void createTestCourses() throws ServiceException {
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(courses.getURI())).lines()) {
            courseDao.saveAll(lines.map(line -> line.split(SEMICOLON)).map(array -> {
                CourseEntity courseEntity = new CourseEntity();
                courseEntity.setCourseName(array[COURSE_NAME_INDEX]);
                courseEntity.setCourseDescription(array[COURSE_DESCRIPTION_INDEX]);
                return courseEntity;
            }).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new ServiceException("error while creating test courses", e);
        }

    }

    private void createTestStudents() throws ServiceException {
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(students.getURI())).lines()) {
            studentDao.saveAll(lines.map(line -> line.split(SEMICOLON)).map(array -> {
                StudentEntity student = new StudentEntity();
                student.setFirstName(array[STUDENT_FIRST_NAME_INDEX]);
                student.setLastName(array[STUDENT_LAST_NAME_INDEX]);
                student.setGroup(groupDao.getByName((array[STUDENT_GROUP_INDEX])));
                return student;
            }).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new ServiceException("error while creating test students", e);
        }
    }

    private void createTestTeachers() throws ServiceException {
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(teachers.getURI())).lines()) {
            teacherDao.saveAll(lines.map(line -> line.split(SEMICOLON)).map(array -> {
                TeacherEntity teacher = new TeacherEntity();
                teacher.setFirstName(array[TEACHER_FIRST_NAME_INDEX]);
                teacher.setLastName(array[TEACHER_LAST_NAME_INDEX]);
                return teacher;
            }).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new ServiceException("error while creating test teachers", e);
        }
    }

    private void createTestTimeTables() throws ServiceException {
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(timeTables.getURI())).lines()) {
            timeTableItemDao.saveAll(lines.map(line -> line.split(SEMICOLON)).map(array -> {
                TimeTableItemEntity timeTable = new TimeTableItemEntity();
                timeTable.setDate(LocalDate.parse(array[TIME_TABLE_DATE_INDEX]));
                timeTable.setStartTime(LocalTime.parse(array[TIME_TABLE_START_TIME_INDEX]));
                timeTable.setEndTime(LocalTime.parse(array[TIME_TABLE_END_TIME_INDEX]));
                timeTable.setCourse(courseDao.getByName(array[TIME_TABLE_COURSE_NAME_INDEX]));
                timeTable.setGroup(groupDao.getByName(array[TIME_TABLE_GROUP_NAME_INDEX]));
                timeTable.setTeacher(teacherDao.getByName(array[TIME_TABLE_TEACHER_LAST_NAME_INDEX]));
                return timeTable;
            }).collect(Collectors.toList()));
        } catch (IOException e) {
            throw new ServiceException("error while creating test timetables", e);
        }
    }

}
