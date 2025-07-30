package com.example.mockdatagenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockDataController {

    private final MockDataService mockDataService;

    public MockDataController(MockDataService mockDataService) {
        this.mockDataService = mockDataService;
    }

    @GetMapping("/health")
    public String health() {
        return "Service is running";
    }

    @GetMapping("/generate")
    public String generateData() {
        mockDataService.generateMockData();
        return "Mock data generation started";
    }
}