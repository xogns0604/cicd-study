package com.cicd_study.service;


import com.cicd_study.domain.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Test
    void createTodo() {
        // given
        String title = "Test Todo";

        // when
        Todo created = todoService.create(title);

        // then
        assertThat(created.getTitle()).isEqualTo(title);
        assertThat(created.isCompleted()).isFalse();
    }

    @Test
    void completeTodo() {
        // given
        Todo todo = todoService.create("Test Todo");

        // when
        Todo completed = todoService.complete(todo.getId());

        // then
        assertThat(completed.isCompleted()).isTrue();
    }

    @Test
    void failureTest() {
        // given
        String title = "Test Todo";
        Todo todo = todoService.create(title);

        // when & then
        // 일부러 실패하는 테스트
        assertThat(todo.isCompleted()).isTrue();
    }
}