package ua.com.foxminded.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ua.com.foxminded.model.entity.TimeTableItemEntity;

@Getter
@Setter
@AllArgsConstructor
public class TimeTable {

    private List<TimeTableItemEntity> listItems;
}
