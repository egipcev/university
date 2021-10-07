package ua.com.foxminded.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableItem {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Course course;
    private Group group;
    private Teacher teacher;
}
