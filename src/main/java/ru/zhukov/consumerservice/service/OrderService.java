package ru.zhukov.consumerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zhukov.consumerservice.model.dto.OrderDto;
import ru.zhukov.consumerservice.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream()
                .map(OrderDto::new)
                .toList();
    }
}
