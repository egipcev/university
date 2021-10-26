package controller.dao;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ua.com.foxminded.config.AppConfig;
import ua.com.foxminded.controller.dao.CourseDao;
import ua.com.foxminded.controller.dao.GroupDao;
import ua.com.foxminded.controller.dao.StudentDao;
import ua.com.foxminded.controller.dao.TeacherDao;
import ua.com.foxminded.controller.dao.TimeTableItemDao;
import ua.com.foxminded.controller.exception.DaoException;
import ua.com.foxminded.model.Course;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.model.Student;
import ua.com.foxminded.model.Teacher;
import ua.com.foxminded.model.TimeTableItem;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class DaoBaseTest {

    private static final String CREATE_TABLES = "CREATE_TABLES.sql";
    private static final String GROUPS = "groups.txt";
    private static final String COURSES = "courses.txt";
    private static final String STUDENTS = "students.txt";
    private static final String TEACHERS = "teachers.txt";
    private static final String TIME_TABLES = "time_tables.txt";
    private static final int COURSE_NAME_INDEX = 0;
    private static final int COURSE_DESCRIPTION_INDEX = 1;
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
    private static final int TIME_TABLE_FIRST_NAME_INDEX = 5;
    private static final int TIME_TABLE_LAST_NAME_INDEX = 6;

    @Autowired
    private BasicDataSource dataSource;

    @Autowired
    protected StudentDao studentDao;

    @Autowired
    protected GroupDao groupDao;

    @Autowired
    protected CourseDao courseDao;

    @Autowired
    protected TeacherDao teacherDao;

    @Autowired
    protected TimeTableItemDao timeTableItemDao;

    @BeforeEach
    void prepareTestData() throws DaoException {
        createTables();
        try {
            groupDao.insertGroups(Files.newBufferedReader(Paths.get(getResourceURI(GROUPS))).lines()
                    .map(groupName -> new Group(groupName)).collect(Collectors.toList()));
            courseDao.insertCourses(Files.newBufferedReader(Paths.get(getResourceURI(COURSES))).lines()
                    .map(line -> line.split(SEMICOLON)).map(array -> {
                        return new Course(array[COURSE_NAME_INDEX], array[COURSE_DESCRIPTION_INDEX]);
                    }).collect(Collectors.toList()));
            studentDao.insertStudents(Files.newBufferedReader(Paths.get(getResourceURI(STUDENTS))).lines()
                    .map(line -> line.split(SEMICOLON)).map(array -> {
                        Student student = new Student();
                        student.setId(UUID.randomUUID().toString());
                        student.setFirstName(array[STUDENT_FIRST_NAME_INDEX]);
                        student.setLastName(array[STUDENT_LAST_NAME_INDEX]);
                        student.setGroup(new Group(array[STUDENT_GROUP_INDEX]));
                        return student;
                    }).collect(Collectors.toList()));
            teacherDao.insertTeachers(Files.newBufferedReader(Paths.get(getResourceURI(TEACHERS))).lines()
                    .map(line -> line.split(SEMICOLON)).map(array -> {
                        Teacher teacher = new Teacher();
                        teacher.setId(UUID.randomUUID().toString());
                        teacher.setFirstName(array[TEACHER_FIRST_NAME_INDEX]);
                        teacher.setLastName(array[TEACHER_LAST_NAME_INDEX]);
                        return teacher;
                    }).collect(Collectors.toList()));
            timeTableItemDao.insertTimeTableItems(Files.newBufferedReader(Paths.get(getResourceURI(TIME_TABLES)))
                    .lines().map(line -> line.split(SEMICOLON)).map(array -> {
                        TimeTableItem timeTable = new TimeTableItem();
                        Teacher teacher = new Teacher();
                        teacher.setFirstName(array[TIME_TABLE_FIRST_NAME_INDEX]);
                        teacher.setLastName(array[TIME_TABLE_LAST_NAME_INDEX]);
                        timeTable.setDate(LocalDate.parse(array[TIME_TABLE_DATE_INDEX]));
                        timeTable.setStartTime(LocalTime.parse(array[TIME_TABLE_START_TIME_INDEX]));
                        timeTable.setEndTime(LocalTime.parse(array[TIME_TABLE_END_TIME_INDEX]));
                        timeTable.setCourse(new Course(array[TIME_TABLE_COURSE_NAME_INDEX], ""));
                        timeTable.setGroup(new Group(array[TIME_TABLE_GROUP_NAME_INDEX]));
                        timeTable.setTeacher(teacher);
                        return timeTable;
                    }).collect(Collectors.toList()));
        } catch (IOException | URISyntaxException e) {
            fail(e);
        }

    }

    private void createTables() {
        try {

            ScriptUtils.executeSqlScript(dataSource.getConnection(),
                    new FileSystemResource(Paths.get(getResourceURI(CREATE_TABLES))));
        } catch (SQLException | URISyntaxException | IOException e) {
            e.printStackTrace();
            fail("error while creating tables");
        }
    }

    public static URI getResourceURI(String fileName) throws IOException, URISyntaxException {
        URL url = ClassLoader.getSystemResource(fileName);
        if (url == null)
            throw new IOException();
        return url.toURI();
    }

}
