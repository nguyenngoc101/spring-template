package com.ngocnv.springtemplate.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ngocnv.springtemplate.entity.ToDo;

@RestController
@RequestMapping("/api")
public class ToDoController {
	
	@GetMapping("/todos")
	public List<ToDo> getToDos() {
		return Collections.emptyList();
	}
	
	@PostMapping("/todos")
	public ToDo createTodo(@Valid @RequestBody ToDo todo) {
		System.out.println("Todo: "+todo);
		return todo;
	}
}
