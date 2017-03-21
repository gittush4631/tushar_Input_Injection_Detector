package com.cluster;

//package cluster;

import java.util.Date;
                                                                           
import javax.jms.Topic;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.jms.TopicSession;
import javax.jms.TopicPublisher;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
                                                                           
import javax.naming.InitialContext;
                                                                           
/**
   This program shows how to write a fault-tolerant topic publisher
   that connects to any server in a jBroker MQ cluster. If a connection
   fails, jBroker MQ automatically fails the publisher over to another
   available server.
 */
                                                                           
public class Publisher
{
    TopicSession _session;
    TopicPublisher _publisher;
                                                                           
    String lookup = "topic://localhost:53506,localhost:53507/";
                                                                           
    /**
       Main program - create and connect a topic publisher.
       @param args command line parameters
       @exception Exception if connecting to server fails
     */
    public static void main(String[] args) throws Exception
    {
       Publisher pub = new Publisher();
       pub.connect();
       while (true) {
          Thread.sleep(1000);
          pub.publish();
       }
    }
                                                                           
    /**
       Connect a topic publisher.
       @exception Exception if connecting to server fails
     */
    public void connect() throws Exception
    {
       InitialContext ctx = new InitialContext();
       Topic topic = (Topic) ctx.lookup("topic://topic0");
       TopicConnectionFactory fac = (TopicConnectionFactory) ctx.
           lookup(lookup + "connectionFactory");
       TopicConnection conn = fac.createTopicConnection();
       _session = conn.createTopicSession(false,
           Session.AUTO_ACKNOWLEDGE);
       _publisher = _session.createPublisher(topic);
    }   
                                                                           
    /**
       Publish a TextMessage to topic
       @exception JMSException if publish failed
     */
    public void publish() throws JMSException
    {
       String text = new Date().toString();
       Message msg = _session.createTextMessage(text);
       _publisher.publish(msg);
       System.out.println(msg);
    }
}
