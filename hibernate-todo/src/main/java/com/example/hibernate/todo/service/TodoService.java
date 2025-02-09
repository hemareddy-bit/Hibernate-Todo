package com.example.hibernate.todo.service;

import com.example.hibernate.todo.model.Todo;
import com.example.hibernate.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (updatedTodo.getTitle() != null) {
            existingTodo.setTitle(updatedTodo.getTitle());
        }

        if (updatedTodo.getDescription() != null) {
            existingTodo.setDescription(updatedTodo.getDescription());
        }

        return todoRepository.save(existingTodo);
    }

    // ✅ Add this method to fix the error
    public void deleteTodo(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo not found");
        }
        todoRepository.deleteById(id);
    }
}
