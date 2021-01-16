package com.epam.preprod.task.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static com.epam.preprod.task.Constants.*;

public class ConsumerFibonacci {

    private static final Logger LOGGER = LogManager.getLogger(ConsumerFibonacci.class);

    private ConsumerFibonacci() {
    }

    public static void runConsumer(int m) {

        Consumer<Long, Long> consumer = ConsumerCreator.createConsumer();

        // counter how many empty messages permissible
        int noMessageCounter = 0;
        // counter how many m Fibonacci numbers
        AtomicInteger mCounter = new AtomicInteger();
        // sum m Fibonacci numbers
        AtomicLong sumFibonacciValues = new AtomicLong();

        while (true) {
            ConsumerRecords<Long, Long> consumerRecords = consumer.poll(Duration.ofMillis(MESSAGE_WAITING_TIME));

            // if message is empty - finish reading or finish current iteration
            if (consumerRecords.count() == 0) {
                noMessageCounter++;
                if (noMessageCounter > MAX_NO_MESSAGE_NUMBER) {
                    break;
                } else {
                    continue;
                }
            }

            // if message is not empty (check above) and within the range m
            if (mCounter.get() < m) {
                consumerRecords.forEach(record -> {
                    // current Fibonacci value add to sum
                    sumFibonacciValues.addAndGet(record.value());
                    mCounter.getAndIncrement();
                    // output and logging message
                    LOGGER.info(String.format(CONSUMER_OUTPUT_FORMAT, record.key(), record.value()));
                });
            } else {
                break;
            }
        }
        // output and logging sum Fibonacci numbers
        LOGGER.info(String.format(CONSUMER_OUTPUT_SUM, sumFibonacciValues.get()));
        consumer.close();
    }
}
