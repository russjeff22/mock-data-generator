package com.example.mockdatagenerator;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MockDataService {
    private final ClientRepository repo;
    private final Random rnd = new Random();

    public MockDataService(ClientRepository repo) {
        this.repo = repo;
    }

    public void generateMockData(int count) {
        List<Client> batch = new ArrayList<>(100);
        for (int i = 0; i < count; i++) {
            Client c = new Client();

            String first = pick(new String[]{"Ava","Liam","Maya","Noah","Zoe","Eli","Ivy","Jack"});
            String last  = pick(new String[]{"Smith","Johnson","Lee","Patel","Garcia","Brown","Martin","Taylor"});
            String full  = first + " " + last;

            // Fill REQUIRED fields
            c.setClientName(full);          // optional column we use
            c.setName(full);                // REQUIRED (NOT NULL in DB)
            c.setEmail((first + "." + last + rnd.nextInt(10000) + "@example.com").toLowerCase());

            // Optional fields your table accepts
            c.setAddress((100 + rnd.nextInt(900)) + " Main St");

            batch.add(c);
            if (batch.size() == 100) {
                repo.saveAll(batch);   // one batched round-trip instead of 100 separate inserts
                batch.clear();
            }
        }
        if (!batch.isEmpty()) {
            repo.saveAll(batch);
        }
    }

    private String pick(String[] arr) { return arr[rnd.nextInt(arr.length)]; }
}
