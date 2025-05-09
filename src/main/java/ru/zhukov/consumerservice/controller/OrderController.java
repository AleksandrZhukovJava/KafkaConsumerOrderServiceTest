package ru.zhukov.consumerservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zhukov.consumerservice.model.dto.OrderDto;
import ru.zhukov.consumerservice.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get/all")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }
}
