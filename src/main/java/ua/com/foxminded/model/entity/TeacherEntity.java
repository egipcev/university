package ua.com.foxminded.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class TeacherEntity extends Person {

    public TeacherEntity(String id, String firstName, String lastName) {
        super(Integer.valueOf(id), firstName, lastName);
    }

}
