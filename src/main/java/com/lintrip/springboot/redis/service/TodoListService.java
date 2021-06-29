package com.lintrip.springboot.redis.service;

import com.lintrip.springboot.redis.domain.Event;
import com.lintrip.springboot.redis.domain.ToDoList;
import com.lintrip.springboot.redis.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ToDoList Service
 */
@Service
public class TodoListService {

    @Resource
    TodoRepository todoRepository;

    public ToDoList getUserToDoList(Long userId) {
        ToDoList toDoList  = new ToDoList();
        toDoList.setUserId(userId);
        List<Event> userToDoList = todoRepository.getUserToDoList(userId);
        List<Event> userDoneList = todoRepository.getUserDoneList(userId);
        toDoList.setTodoList(userToDoList);
        toDoList.setDoneList(userDoneList);
        return toDoList;
    }

    public void addTodo(Long userId, String todo) {
        Event event = new Event();
        event.setDescription(todo);
        Date now = new Date();
        event.setCreatTime(new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(now));
        todoRepository.save(userId,event);
    }
}
