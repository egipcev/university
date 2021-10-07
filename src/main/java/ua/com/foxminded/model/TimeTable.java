package ua.com.foxminded.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TimeTable {

    private List<TimeTableItem> listItems;
}
