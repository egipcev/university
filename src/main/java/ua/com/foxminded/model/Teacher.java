package ua.com.foxminded.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Teacher extends Person {

    public Teacher(String id) {
        super(id);
    }

    public Teacher(String id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

}
