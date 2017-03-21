package com.cluster;

import javax.jms.Topic;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.JMSException;
import javax.jms.TopicSession;
import javax.jms.TopicConnection;
import javax.jms.TopicSubscriber;
import javax.jms.MessageListener;
import javax.jms.ExceptionListener;
import javax.jms.TopicConnectionFactory;                                                                           
import javax.*;
import javax.naming.InitialContext;
                                                                           
//import com.sssw.jms.api.JMQConnectionLostException;
                                                                           
/**
   This program shows how to write a fault-tolerant topic subscriber
   that connects to any server in a jBroker MQ cluster. If a connection
   fails, the subscriber re-issues the connect command to connect to
   any available server.
 */
                                                                           
public class Subscriber implements MessageListener, ExceptionListener
{
    String lookup = "topic://localhost:53506,localhost:53507/";
                                                                           
    /**
       Main program - create and connect a topic subscriber.
       @param args command line parameters
       @exception Exception if connecting to server fails
     */
    public static void main(String[] args) throws Exception
    {
       Subscriber sub = new Subscriber();
       sub.connect();
       System.out.println("subscriber ready to process messages ...");
       while (true) {
          synchronized (sub) { sub.wait(); }
       }
    }
                                                                           
    /**
       Connect a topic subscriber.
       @exception Exception if connecting to server fails
     */
    public void connect() throws Exception
    {
       InitialContext ctx = new InitialContext();
       Topic topic = (Topic) ctx.lookup("topic://topic0");
       TopicConnectionFactory fac = (TopicConnectionFactory) ctx.
           lookup(lookup + "connectionFactory");
       TopicConnection conn = fac.createTopicConnection();
       conn.start();
       TopicSession session = conn.createTopicSession(false,
           Session.AUTO_ACKNOWLEDGE);
       conn.setExceptionListener(this);
       TopicSubscriber subscriber = session.createSubscriber(topic);
       subscriber.setMessageListener(this);
    }   
                                                                           
    /**
       Receive message asynchronously.
       @param message incoming JMS message
     */
    public void onMessage(Message message)
    {
       System.out.println(message);
    }
                                                                           
    /**
       Intercept lost connection exception.
       @param ex a JMSException
     */
    public void onException(JMSException ex)
    {
//       if (ex instanceof JMQConnectionLostException)
//       {
//         try {
//             connect();
//             System.out.println("connected to other server ...");
//          } catch (Exception exc) {
//             System.out.println("failed to connect: " + exc);
//             System.exit(1);
//          }
//       }
//       else
//       {
//          ex.printStackTrace(); // other error handling ...
//       }
    }
}