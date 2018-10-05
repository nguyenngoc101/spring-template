package com.ngocnv.springtemplate.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ngocnv.springtemplate.entity.ToDo;

@RestController
@RequestMapping("/api")
public class ToDoController {

	final Logger logger = LoggerFactory.getLogger(ToDoController.class);

	@GetMapping("/todos")
	public List<ToDo> getToDos() {
        ToDo toDo = new ToDo("taks 1", new Date(0));
		logger.info("info: list all todos {}", toDo);
        logger.debug("debug: list all todos", toDo);
        logger.warn("warn: list all todos", toDo);
		logger.error("error: list all todos", toDo);
		return Collections.emptyList();
	}
	
	@PostMapping("/todos")
	public ToDo createTodo(@Valid @RequestBody ToDo todo) {
        System.out.println("Todo: " + todo);
		return todo;
	}
}
