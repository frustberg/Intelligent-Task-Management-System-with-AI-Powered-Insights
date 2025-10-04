package com.frustberg.taskservice.controller;

import com.frustberg.taskservice.model.Task;
import com.frustberg.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired private TaskService svc;
    @PostMapping public ResponseEntity<Task> create(@RequestBody Task t){ return ResponseEntity.ok(svc.createTask(t)); }
    @GetMapping("/user/{userId}") public ResponseEntity<List<Task>> forUser(@PathVariable String userId){ return ResponseEntity.ok(svc.getTasksForUser(userId)); }
    @PutMapping("/{id}") public ResponseEntity<Task> update(@PathVariable String id,@RequestBody Task t){ t.setId(id); return ResponseEntity.ok(svc.updateTask(t)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable String id){ svc.deleteTask(id); return ResponseEntity.noContent().build(); }
}
