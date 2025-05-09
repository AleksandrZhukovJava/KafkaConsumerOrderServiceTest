package ru.zhukov.consumerservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID externalId;
    private String customerName;
    @CreationTimestamp
    private Instant dateCreated;
    private Instant externalDateCreated;
    private int itemAmount;

    @Builder
    public Order(UUID externalId,
                 String customerName,
                 Instant externalDateCreated,
                 int itemAmount) {
        this.externalId = externalId;
        this.customerName = customerName;
        this.externalDateCreated = externalDateCreated;
        this.itemAmount = itemAmount;
    }
}
