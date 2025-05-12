package ru.zhukov.consumerservice.config;

import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    @Primary
    public ConcurrentKafkaListenerContainerFactory<UUID, Object> kafkaListenerContainerFactory() {
        JsonDeserializer<Object> valueDeserializer = new JsonDeserializer<>(Object.class);
        valueDeserializer.addTrustedPackages("ru.zhukov.kafkalibrary.*");

        Map<String, Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, UUIDDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer.getClass());

        DefaultKafkaConsumerFactory<UUID, Object> kafkaConsumerFactory =
                new DefaultKafkaConsumerFactory<>(props, new UUIDDeserializer(), valueDeserializer);

        ConcurrentKafkaListenerContainerFactory<UUID, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        return factory;
    }
}