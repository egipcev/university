package ua.com.foxminded.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDto extends PersonDto {

    public TeacherDto(String id) {
        super(Integer.valueOf(id));
    }

    public TeacherDto(String id, String firstName, String lastName) {
        super(Integer.valueOf(id), firstName, lastName);
    }

}
