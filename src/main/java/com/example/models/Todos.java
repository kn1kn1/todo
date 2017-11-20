package com.example.models;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Todos {
	private final static Todos instance = new Todos();
	private final Map<String, Todo> todos = new LinkedHashMap<String, Todo>();
	private long lastId = -1;

	private Todos() {
	}

	public static Todos getInstance() {
		return instance;
	}

	public Todo[] getTodos() {
		return todos.values().toArray(new Todo[todos.size()]);
	}

	public Todo getTodo(String id) {
		Todo todo = todos.get(id);
		return todo;
	}

	public void addTodo(Todo todo) {
		synchronized (this) {
			lastId++;
			String id = Long.toString(lastId);
			todo.setId(id);
			todos.put(id, todo);
		}
	}

	public Todo delteTodo(String id) {
		Todo removed = todos.remove(id);
		return removed;
	}

	public Todo[] searchTodo(String word) {
		Set<Todo> results = new HashSet<Todo>();
		for (Entry<String, Todo> entry : todos.entrySet()) {
			Todo todo = entry.getValue();
			if (todo.getTitle().contains(word)) {
				results.add(todo);
				continue;
			}
			if (todo.getContents().contains(word)) {
				results.add(todo);
			}
		}
		return (Todo[]) results.toArray(new Todo[results.size()]);
	}

	public void clear() {
		synchronized (this) {
			todos.clear();
			lastId = -1;
		}
	}
}
