package ru.zhukov.consumerservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaOrder {
    private KafkaEventType type;
    private UUID aggregateId;
    private Instant dateCreated;
    private String customerName;
    private Instant orderDateCreated;
    private int itemAmount;
}
