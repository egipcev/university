package model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableItem {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Course course;
    private Group group;
    private Teacher teacher;
}
