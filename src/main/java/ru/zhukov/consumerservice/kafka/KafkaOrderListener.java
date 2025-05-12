package ru.zhukov.consumerservice.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.zhukov.consumerservice.model.Order;
import ru.zhukov.consumerservice.repository.OrderRepository;
import ru.zhukov.kafkalibrary.order.OrderCreateEvent;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaOrderListener {
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "${client.topics.order}", groupId = "order-consumer-group")
    public void listen(ConsumerRecord<UUID, OrderCreateEvent> record, Acknowledgment acknowledgment) {
        log.info("Получен заказ из кафки: [{}]", record.value());
        //В сервис
        try {
            if (orderRepository.existsByExternalId(record.key())) {
                throw new RuntimeException("такое есть уже");
            }
            OrderCreateEvent order = record.value();
            orderRepository.save(mapToOrder(order));
        } finally {
            acknowledgment.acknowledge();
        }
    }

    //mapper
    private Order mapToOrder(OrderCreateEvent kafkaOrder) {
        return Order.builder()
                .customerName(kafkaOrder.getCustomerName())
                .externalDateCreated(kafkaOrder.getDateCreated())
                .externalId(kafkaOrder.getOrderId())
                .itemAmount(kafkaOrder.getItemAmount())
                .build();
    }
}
