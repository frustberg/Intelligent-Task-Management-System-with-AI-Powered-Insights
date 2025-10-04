package com.frustberg.analyticsservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Map;

@FeignClient(name = "task-service", url = "${task-service.url}")
public interface TaskServiceClient {
    @GetMapping("/user/{userId}")
    List<Map<String,Object>> getTasksForUser(@PathVariable("userId") String userId);
}
