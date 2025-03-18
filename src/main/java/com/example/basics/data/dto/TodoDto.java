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
public class TodoDto {
    private Long id;
    private String title;
    private List<TaskDto> tasks;

    public TodoDto(Todo todo){
        id = todo.getId();
        title = todo.getTitle();
        tasks = new ArrayList<TaskDto>();

        for(Task task : todo.getTasks()){
            tasks.add(new TaskDto(task));
        }
    }
}
