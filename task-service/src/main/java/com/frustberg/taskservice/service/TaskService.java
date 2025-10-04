package com.frustberg.taskservice.service;

import com.frustberg.taskservice.model.Task;
import com.frustberg.taskservice.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired private TaskRepository repo;
    public Task createTask(Task t){ return repo.save(t); }
    public List<Task> getTasksForUser(String userId){ return repo.findByAssignedTo(userId); }
    public Optional<Task> getTaskById(String id){ return repo.findById(id); }
    public Task updateTask(Task t){ return repo.save(t); }
    public void deleteTask(String id){ repo.deleteById(id); }
}
