package ua.com.foxminded.model;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class Group {
    final String groupName;
    List<Student> listStudents;
    List<Course> listCourses;
    TimeTable timeTable;

}
