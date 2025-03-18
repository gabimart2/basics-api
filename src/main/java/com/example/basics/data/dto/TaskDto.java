package com.example.basics.data.dto;

import com.example.basics.data.db.Task;
import com.example.basics.data.db.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;

    public TaskDto(Task task){
        id = task.getId();
        title = task.getTitle();

    }
}
