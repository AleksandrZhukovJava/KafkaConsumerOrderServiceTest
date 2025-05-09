package ru.zhukov.consumerservice.model.dto;

import lombok.Data;
import ru.zhukov.consumerservice.model.Order;

import java.time.Instant;
import java.util.UUID;

@Data
public class OrderDto {
    private Long id;
    private UUID externalId;
    private String customerName;
    private Instant dateCreated;
    private Instant externalDateCreated;
    private int itemAmount;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.externalId = order.getExternalId();
        this.customerName = order.getCustomerName();
        this.dateCreated = order.getDateCreated();
        this.externalDateCreated = order.getExternalDateCreated();
        this.itemAmount = order.getItemAmount();
    }
}
