package com.example.userservice.rabbitmq;

import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQListener {

    private final UserService userService;

    public RabbitMQListener(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public Queue myQueue(){
        return new Queue("messages", false);
    }

    @RabbitListener(queues = "messages")
    public void listenToMessageQueue(String jsonMessage) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonMessage);
            String userID = jsonNode.get("userID").asText();

            userService.incrementMessageCount(userID);

        } catch (Exception e) {
            System.out.println("Error in RabbitMQListener: " + e.getMessage());
        }
    }
}
