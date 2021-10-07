package ua.com.foxminded.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Teacher extends Person {

    private List<Group> listGroups;
    private List<Course> listCourses;
    private TimeTable timeTable;

}
