package com.example.shop.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "kafka.enabled", matchIfMissing = false)
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrapAddress}")
    private String SERVER;

    @Value("${spring.kafka.topic}")
    private String ORDER_TOPIC;

    private ProducerFactory<String, byte[]> producerFactoryByteArray() {// задаёт стратегию создания продюсера
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,//в этом свойстве указываются адреса брокеров Kafka
                SERVER);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,// серилизация ключа
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,//серилизация значения
                ByteArraySerializer.class);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG,1);// распределяет сообщения между partitions

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, byte[]> kafkaTemplateByteArray() {
        return new KafkaTemplate<>(producerFactoryByteArray());
    }

    private ProducerFactory<String, String> producerFactoryString() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                SERVER);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG,1);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateString() {
        return new KafkaTemplate<>(producerFactoryString());
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {//KafkaAdmin отвечает за создание новых топиков в нашем брокере
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic orderTopic() {// создание топика
        return TopicBuilder
                .name(ORDER_TOPIC)
                .partitions(2)
                .replicas(1)
                .build();
    }

}
