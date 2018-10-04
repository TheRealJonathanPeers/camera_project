package be.kdg.processor.messaging.consumers;

import be.kdg.processor.models.CameraMessage;
import be.kdg.processor.transformers.MessageTransformer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class QueueConsumer {

    private static final Logger log = LoggerFactory.getLogger(QueueConsumer.class);
    private final MessageTransformer messageTransformer;
    private final ArrayList<CameraMessage> cameraMessages; //doesnt share relation with arraylist in the simulator module
    private final RabbitTemplate template;
    private final ArrayList<String> licensePlates;


    public QueueConsumer(MessageTransformer messageTransformer, ArrayList<CameraMessage> cameraMessages, RabbitTemplate template, ArrayList<String> licensePlates) {
        this.messageTransformer = messageTransformer;
        this.cameraMessages = cameraMessages;
        this.template = template;
        this.licensePlates = licensePlates;
    }

    //    https://docs.spring.io/spring-amqp/reference/htmlsingle/#message-builder
    @RabbitListener(queues = "camera-queue")
    public void consume(String in) {
        XmlMapper mapper = new XmlMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        mapper.registerModule(javaTimeModule);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try {
            CameraMessage cameraMessage = mapper.readValue(in, CameraMessage.class);
            log.info("Message received: " + cameraMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
