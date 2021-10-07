package ua.com.foxminded.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Course {
    private String courseName;
    private String courseDescription;
}
