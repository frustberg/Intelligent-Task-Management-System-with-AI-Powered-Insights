package com.frustberg.taskservice.repository;

import com.frustberg.taskservice.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByAssignedTo(String assignedTo);
}
