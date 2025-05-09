package ru.zhukov.consumerservice.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import ru.zhukov.consumerservice.model.Order;
import ru.zhukov.consumerservice.repository.OrderRepository;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaOrderListener {
    private final OrderRepository orderRepository;

    @KafkaListener(topics = "${client.topics.order}", groupId = "order-consumer-group")
    public void listen(ConsumerRecord<UUID, KafkaOrder> record, Acknowledgment acknowledgment) {
        log.info("Получен заказ из кафки: [{}]", record.value());
        //В сервис
        try {
            if (orderRepository.existsByExternalId(record.key())) {
                throw new RuntimeException("такое есть уже");
            }
            KafkaOrder order = record.value();
            orderRepository.save(mapToOrder(order));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            acknowledgment.acknowledge();
        }
    }

    //mapper
    private Order mapToOrder(KafkaOrder kafkaOrder) {
        return Order.builder()
                .customerName(kafkaOrder.getCustomerName())
                .externalDateCreated(kafkaOrder.getDateCreated())
                .externalId(kafkaOrder.getAggregateId())
                .itemAmount(kafkaOrder.getItemAmount())
                .build();
    }
}
