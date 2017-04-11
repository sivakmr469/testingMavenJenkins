package test;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PahoDemo implements MqttCallback {

MqttClient client;

public PahoDemo() {
}

public static void main(String[] args) {
    new PahoDemo().doDemo();
}

public void doDemo() {
    MemoryPersistence persistence = new MemoryPersistence();
    int qos             = 2;
    try {
        client = new MqttClient("tcp://localhost:1883", "JavaSample",persistence);
        client.connect();
        client.setCallback(this);
        client.subscribe("pahodemo/test");
        MqttMessage message = new MqttMessage();
        message.setPayload("A single message from my computer fff"
                .getBytes());


        message.setQos(1);
        message.setPayload("dir".getBytes());        
        client.publish("pahodemo/test", message);
        client.subscribe("pahodemo/test");
        
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

@Override
public void connectionLost(Throwable cause) {
    // TODO Auto-generated method stub

}

@Override
public void messageArrived(String topic, MqttMessage message)
        throws Exception {
 System.out.println(topic +":::"+message);   
}

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    // TODO Auto-generated method stub
	System.out.println("deliveryComplete");

}

}