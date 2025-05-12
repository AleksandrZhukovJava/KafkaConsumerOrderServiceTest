package ru.zhukov.consumerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zhukov.consumerservice.repository.OrderRepository;
import ru.zhukov.kafkalibrary.order.OrderCreateEvent;

@Service
@RequiredArgsConstructor
public class InternalOrderService {
    private final OrderRepository orderRepository;

    //todo
    public void addOrderFromKafka(OrderCreateEvent OrderCreateEvent) {
    }
}
