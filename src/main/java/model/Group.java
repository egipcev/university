package model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Group {
    String groupName;
    List<Student> listStudents;
    List<Course> listCourses;
    TimeTable timeTable;

}
