package com.todo.javiergrande.model;

public class TodoItem {
    String content;
    boolean completed;

    public TodoItem(String content) {
        this.content = content;
        this.completed = false;
    }
    
    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    public void toggleCompleted() {
        this.completed = !this.completed;
    }
}
