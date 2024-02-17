
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoJpaRepository;
import com.example.todo.repository.TodoRepository;


@Service
public class TodoJpaService implements TodoRepository {

    @Autowired
    public TodoJpaRepository db;

    @Override
    public ArrayList<Todo> getTodos() {
        List<Todo> todoData = db.findAll();
        ArrayList<Todo> todos = new ArrayList<>(todoData);
        return todos;
    }

    @Override
    public Todo getTodoById(int id) {
        try {
            Todo todo = db.findById(id).get();
            return todo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo addTodo(Todo todo) {
       db.save(todo);
        return todo;
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
         try {
            Todo newtodo = db.findById(id).get();
        if (todo.getTodo() != null) {
          newtodo.setTodo(todo.getTodo());
        }
        if (todo.getPriority() != null) {
          newtodo.setPriority(todo.getPriority());
        }
        if (todo.getStatus() != null) {
            newtodo.setStatus(todo.getStatus());
        }
        db.save(newtodo);
        return newtodo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       
    }

    @Override
    public void deleteTodo(int id) {
       try {
          db.deleteById(id);
          
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}