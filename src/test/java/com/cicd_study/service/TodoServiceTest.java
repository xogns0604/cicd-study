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
        // given test
        Todo todo = todoService.create("Test Todo");

        // when
        Todo completed = todoService.complete(todo.getId());

        // then
        assertThat(completed.isCompleted()).isTrue();
    }

    @Test
    void updateTodoTitle() {
        // given
        Todo todo = todoService.create("Original Title");
        String newTitle = "Updated Title";

        // when
        Todo updated = todoService.updateTitle(todo.getId(), newTitle);

        // then
        assertThat(updated.getTitle()).isEqualTo(newTitle);
        assertThat(updated.getId()).isEqualTo(todo.getId());  // 동일한 Todo인지 확인
        assertThat(updated.isCompleted()).isEqualTo(todo.isCompleted());  // 완료 상태는 변경되지 않았는지 확인
    }

}