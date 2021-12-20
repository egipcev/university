package ua.com.foxminded.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student extends Person {

    public Student(String id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public Student(String id) {
        super(id);
    }

    private Group group;
}
