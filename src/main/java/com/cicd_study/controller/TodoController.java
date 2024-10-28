package com.cicd_study.controller;

import com.cicd_study.domain.entity.Todo;
import com.cicd_study.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody TodoRequest request) {
        return ResponseEntity.ok(todoService.create(request.title()));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> findAll() {
        return ResponseEntity.ok(todoService.findAll());
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Todo> complete(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.complete(id));
    }
}