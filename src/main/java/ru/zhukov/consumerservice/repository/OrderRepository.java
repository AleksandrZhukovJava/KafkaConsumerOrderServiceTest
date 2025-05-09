package ru.zhukov.consumerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zhukov.consumerservice.model.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByExternalId(UUID externalId);
}
