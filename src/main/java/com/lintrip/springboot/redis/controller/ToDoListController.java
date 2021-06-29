package com.lintrip.springboot.redis.controller;

import com.lintrip.springboot.redis.domain.ToDoList;
import com.lintrip.springboot.redis.dto.BaseResponseObject;
import com.lintrip.springboot.redis.service.TodoListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoListController {

    private static final Logger logger = LoggerFactory.getLogger(ToDoListController.class);

    @Autowired
    private TodoListService todoListService;

    @RequestMapping("/todo/get.action")
    public BaseResponseObject<ToDoList> getUserList(Long userId) {
        logger.info("query user todo list,userId is {}!", userId);
        ToDoList toDoList = todoListService.getUserToDoList(userId);
        return BaseResponseObject.success(toDoList);
    }

    @RequestMapping("/todo/addToDoList.action")
    public BaseResponseObject<ToDoList> addToDo(@Param("userId") Long userId, @Param("todo")String todo) {
        logger.info("addToDo,userId is {},toDo is :{}!", userId,todo);
        todoListService.addTodo(userId,todo);
        return BaseResponseObject.success(null);
    }
}
