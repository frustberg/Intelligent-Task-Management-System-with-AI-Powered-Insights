package com.frustberg.aiservice.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AiController {
    @GetMapping("/recommendations/{role}") 
    public List<String> rec(@PathVariable String role) {
        if ("ROLE_ADMIN".equals(role)) return List.of("Review reports","Approve users");
        return List.of("Finish tasks","Prioritize urgent items");
    }
}
