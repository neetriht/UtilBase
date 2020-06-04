package com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * Created by neetriht on 2017-10-29.
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.255.140");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

//        Consumer consumer = new Consumer(channel);
////        channel.basicConsume(QUEUE_NAME, true, consumer);
////
////        while (true) {
////            Consumer.Delivery delivery = consumer.nextDelivery();
////            String message = new String(delivery.getBody());
////            System.out.println(" [x] Received '" + message + "'");
//        }
    }
}
