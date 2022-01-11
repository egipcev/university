package ua.com.foxminded.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {

    private int id;

    private String courseName;

    private String courseDescription;

    public CourseDto(String id) {
        this.id = Integer.valueOf(id);
    }
}
