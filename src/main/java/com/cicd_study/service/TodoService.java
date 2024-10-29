package com.cicd_study.service;


import com.cicd_study.domain.entity.Todo;
import com.cicd_study.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Todo create(String title) {
        return todoRepository.save(new Todo(title));
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Transactional
    public Todo complete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));
        todo.complete();
        return todo;
    }

    @Transactional
    public Todo updateTitle(Long id, String newTitle) {
        if (newTitle == null || newTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));

        todo.updateTitle(newTitle.trim());
        return todo;
    }
}