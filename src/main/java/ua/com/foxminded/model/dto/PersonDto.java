package ua.com.foxminded.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class PersonDto {

    protected PersonDto(Integer id) {
        this.id = id;
    }

    private int id;

    private String firstName;

    private String lastName;

}
