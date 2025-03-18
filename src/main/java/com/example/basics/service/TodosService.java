package com.example.basics.service;

import com.example.basics.data.db.Todo;
import com.example.basics.data.dto.TodoDto;
import com.example.basics.data.request.CreateUpdateTodoRequest;
import com.example.basics.repository.TodoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
public class TodosService {

    @Autowired
    private TodoRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<TodoDto> getAll(){
        List<Todo> todos = repository.getTodos();
        List<TodoDto> result = new ArrayList<TodoDto>();
        for(Todo todo : todos){
            result.add(new TodoDto(todo));
        }
        return result;
    }

    public TodoDto getTodo(String id){
        return new TodoDto(repository.getById(Long.valueOf(id)));
    }

    public Boolean removeTodo(String id){
        Todo todo = repository.getById(Long.valueOf(id));
        if (todo != null){
            repository.delete(todo);
            return true;
        }
        else{
            return false;
        }

    }

    public TodoDto updateTodo(String todoId, String request) {

        Todo hotel = repository.getById(Long.valueOf(todoId));
        if (hotel != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(hotel)));
                Todo patched = objectMapper.treeToValue(target, Todo.class);
                patched = repository.save(patched);
                return new TodoDto(patched);
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating hotel {}", todoId, e);
                return null;
            }
        } else {
            return null;
        }
    }

    public TodoDto createTodo(CreateUpdateTodoRequest request){
        if (request!=null && StringUtils.hasLength(request.getTitle().trim())){
            Todo todo = Todo.builder().title(request.getTitle().trim()).build();
            return new TodoDto(repository.save(todo));
        }
        else{
            return null;
        }
    }
}
