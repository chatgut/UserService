package rabbitmq;

import com.example.userservice.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQListener {

    private final UserService userService;

    public RabbitMQListener(UserService userService) {
        this.userService = userService;
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

    // TODO: fix issue so it establish a connection to QueueRabbitMQ
}
