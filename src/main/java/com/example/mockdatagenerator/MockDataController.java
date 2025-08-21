package com.example.mockdatagenerator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class MockDataController {

    private final MockDataService service;
    private final ClientRepository repo;

    public MockDataController(MockDataService service, ClientRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @PostMapping("/generate")
    public ResponseEntity<Map<String,Object>> generate(@RequestParam int count) {
        service.generateMockData(count);
        return ResponseEntity.ok(Map.of("status", "ok", "generated", count));
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        return Map.of("count", repo.count());
    }
}
