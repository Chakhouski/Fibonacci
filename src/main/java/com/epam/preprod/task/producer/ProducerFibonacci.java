package com.epam.preprod.task.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;

import static com.epam.preprod.task.Constants.PRODUCER_OUTPUT_FORMAT;
import static com.epam.preprod.task.Constants.TOPIC_NAME;

public class ProducerFibonacci {

    private static final Logger LOGGER = LogManager.getLogger(ProducerFibonacci.class);

    private ProducerFibonacci() {
    }

    public static void runProducer(int n) {

        Producer<Long, Long> producer = ProducerCreator.createProducer();

        // Fibonacci values
        long value = 0;
        long nextValue = 1;
        long afterNextValue;

        // send current number (index) and current Fibonacci value to Kafka (at each iteration)
        for (long number = 0; number < n; number++) {

            // create current number, current value, prepare them for logging and record
            long currentNumber = number + 1;
            long currentValue = value;
            ProducerRecord<Long, Long> record = new ProducerRecord<>(TOPIC_NAME, currentNumber, currentValue);

            // offset Fibonacci values
            afterNextValue = value + nextValue;
            value = nextValue;
            nextValue = afterNextValue;

            try {
                // send current number and current value to Kafka
                producer.send(record).get();
                LOGGER.info(String.format(PRODUCER_OUTPUT_FORMAT, currentNumber, currentValue));
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error(e.getMessage());
            }
        }
        producer.close();
    }
}
