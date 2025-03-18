package com.example.basics.data.db;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name="todos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", unique = true)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "todo_id", referencedColumnName = "id")
    private List<Task> tasks;

}
