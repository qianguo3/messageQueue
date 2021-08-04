package com.badu.emebed;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;

public class embedMQ {
    public static void main(String[] args) throws Exception {

        BrokerService brokerService = new BrokerService();

        brokerService.setBrokerName("EmbedMQ");
        brokerService.addConnector("tcp://localhost:62000");
        brokerService.setManagementContext(new ManagementContext());
        brokerService.start();
    }
}
