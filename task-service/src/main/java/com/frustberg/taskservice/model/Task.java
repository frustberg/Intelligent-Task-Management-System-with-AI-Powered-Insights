package com.frustberg.taskservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private String assignedTo;
    private boolean completed;
    private Instant createdAt = Instant.now();

    public Task() {}
    // getters and setters
    public String getId(){return id;} public void setId(String id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getDescription(){return description;} public void setDescription(String d){this.description=d;}
    public String getAssignedTo(){return assignedTo;} public void setAssignedTo(String a){this.assignedTo=a;}
    public boolean isCompleted(){return completed;} public void setCompleted(boolean c){this.completed=c;}
    public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant t){this.createdAt=t;}
}
