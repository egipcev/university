package ua.com.foxminded.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students")
public class StudentEntity extends Person {

    public StudentEntity(String id, String firstName, String lastName) {
        super(Integer.valueOf(id), firstName, lastName);
    }

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;
}
