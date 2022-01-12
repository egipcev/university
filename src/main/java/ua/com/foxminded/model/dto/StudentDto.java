package ua.com.foxminded.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class StudentDto extends PersonDto {

    public StudentDto(String id, String firstName, String lastName) {
        super(Integer.valueOf(id), firstName, lastName);
    }

    public StudentDto(String id) {
        super(Integer.valueOf(id));
    }

    private GroupDto group;
}
