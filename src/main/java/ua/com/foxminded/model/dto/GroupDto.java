package ua.com.foxminded.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupDto {

    private int id;

    private String groupName;

    public GroupDto(String id) {
        this.id = Integer.valueOf(id);
    }

}
