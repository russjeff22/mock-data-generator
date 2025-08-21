package com.example.mockdatagenerator;

import org.springframework.stereotype.Service;

@Service
public class MockDataService {

    private final ClientRepository clientRepository;
    private final com.github.javafaker.Faker faker = new com.github.javafaker.Faker();

    public MockDataService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void generateMockData(int count) {
        for (int i = 0; i < count; i++) {
            Client client = new Client();
            client.setClientName(faker.name().fullName());
            client.setAddress(faker.address().fullAddress());
            client.setEmail(faker.internet().emailAddress());
            clientRepository.save(client);
        }
        System.out.println("Generated " + count + " mock client records.");
    }
}
