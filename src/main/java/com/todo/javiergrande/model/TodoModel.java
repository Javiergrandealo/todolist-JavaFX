package com.todo.javiergrande.model;
import java.util.ArrayList;
import java.util.List;

public class TodoModel {
    private String title;
    private String description;
    private List<TodoItem> items;

    public TodoModel(String title, String description) {
        this.title = title;
        this.description = description;
        this.items = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public void addItem(TodoItem item) {
        items.add(item);
    }

    public void removeItem(TodoItem item) {
        items.remove(item);
    }
    public void markItemCompleted(TodoItem item) {
        if (items.contains(item)) {
            item.markCompleted();
        }
    }
    
}
