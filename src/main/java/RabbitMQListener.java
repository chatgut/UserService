import com.example.userservice.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class RabbitMQListener {

    private final UserService userService;

    @Autowired
    public RabbitMQListener(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "messages")
    public void listenToMessageQueue(@RequestHeader String userID) {
        userService.incrementMessageCount(userID);

    }

}
