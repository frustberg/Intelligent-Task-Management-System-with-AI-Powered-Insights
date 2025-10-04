package com.frustberg.analyticsservice.controller;

import com.frustberg.analyticsservice.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    @Autowired private AnalyticsService svc;
    @GetMapping("/user/{userId}") public Object get(@PathVariable String userId){ return svc.getAnalyticsForUser(userId); }
}
