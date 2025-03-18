package com.example.basics.repository;

import com.example.basics.data.db.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TodoJpaRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {
    Todo findByTitle(String title);
}
