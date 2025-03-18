package com.example.basics.controller;

import com.example.basics.data.db.Todo;
import com.example.basics.data.dto.TodoDto;
import com.example.basics.service.TodosService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TodosController {

    @Autowired
    private TodosService service;

    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getTodos(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable String todoId){
        TodoDto result = service.getTodo(todoId);
        if(result!=null) {
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String todoId){
        Boolean removed = service.removeTodo(todoId);
        if (removed)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }



    @PatchMapping("/todos/{todoId}")
    public ResponseEntity<TodoDto> patchTodo(@PathVariable String todoId, @RequestBody String patchBody) {

        TodoDto patched = service.updateTodo(todoId, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
