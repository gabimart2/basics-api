package com.example.basics.repository;


import com.example.basics.data.db.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {
    private final TodoJpaRepository repository;

    public List<Todo> getTodos(){
        return repository.findAll();
    }

    public Todo getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public void delete(Todo todo){
        repository.delete(todo);
    }

    public Todo save(Todo todo){
        return repository.save(todo);
    }
}
