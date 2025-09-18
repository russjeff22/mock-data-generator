package com.example.mockdatagenerator;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "Client", schema = "dbo")
public class Client {

    @Id
    @Column(name = "client_id", columnDefinition = "uniqueidentifier", nullable = false)
    private UUID clientId;

    // DB requires this NOT NULL. We'll set it at @PrePersist if missing.
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    // Let SQL Server populate this via DEFAULT; don't send a value.
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    // Optional columns your code currently uses in INSERT
    @Column(name = "client_name", length = 100)
    private String clientName;

    @Column(name = "address", length = 255)
    private String address;

    @PrePersist
    public void prePersist() {
        if (clientId == null) clientId = UUID.randomUUID();
        // If 'name' not set, copy from clientName so NOT NULL constraint is satisfied.
        if ((name == null || name.isBlank()) && clientName != null && !clientName.isBlank()) {
            name = clientName;
        }
        // Basic guard: email must not be null for DB; if your service forgot it, set something.
        if (email == null || email.isBlank()) {
            email = "unknown@example.com";
        }
    }

    // ----- Getters & Setters -----
    public UUID getClientId() { return clientId; }
    public void setClientId(UUID clientId) { this.clientId = clientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
