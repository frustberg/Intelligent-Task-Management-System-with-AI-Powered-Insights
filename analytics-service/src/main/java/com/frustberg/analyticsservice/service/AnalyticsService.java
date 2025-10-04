package com.frustberg.analyticsservice.service;

import com.frustberg.analyticsservice.client.TaskServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {
    @Autowired private TaskServiceClient client;
    public Map<String,Object> getAnalyticsForUser(String userId){
        List<Map<String,Object>> tasks = client.getTasksForUser(userId);
        long total = tasks.size();
        long completed = tasks.stream().filter(t -> Boolean.TRUE.equals(t.get("completed"))).count();
        return Map.of("userId", userId, "total", total, "completed", completed, "completionRate", total>0? (completed*100.0/total):0.0);
    }
}
