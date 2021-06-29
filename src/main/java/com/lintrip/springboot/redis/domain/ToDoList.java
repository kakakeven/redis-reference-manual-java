package com.lintrip.springboot.redis.domain;

import java.util.List;

/**
 * 用户 TODO 列表
 */
public class ToDoList {

    private Long userId;

    private List<Event> todoList;

    private List<Event> doneList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Event> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Event> todoList) {
        this.todoList = todoList;
    }

    public List<Event> getDoneList() {
        return doneList;
    }

    public void setDoneList(List<Event> doneList) {
        this.doneList = doneList;
    }
}
