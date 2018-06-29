package com.example.demo.tool;

import com.example.demo.model.event.Event;
import com.example.demo.model.event.EventParameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Ignore;
import org.junit.Test;

import javax.jms.*;

public class JmsTools {

    @Test
    public void JmsSend() throws Exception {

        String str;
        {
            EventParameter eventParameter = new EventParameter();
            eventParameter.setKey("certificateNumber");
            eventParameter.setValue("баркод1");

            Event event = new Event();
            event.setEventType("viewСertificate");
            event.setEventSubType("boxSale");
            event.getParameters().add(eventParameter);

            ObjectMapper objectMapper = new ObjectMapper();
            str = objectMapper.writeValueAsString(event);
        }

        {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("dbo2broker", "broker31415", "tcp://dc1-anketadev01:61619");
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("xxx");
            MessageProducer producer = session.createProducer(queue);

            TextMessage message = session.createTextMessage(str);
            producer.send(message);

            connection.close();
        }
    }
}
