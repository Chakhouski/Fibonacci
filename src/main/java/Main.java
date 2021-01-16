import com.epam.preprod.task.consumer.ConsumerFibonacci;
import com.epam.preprod.task.producer.ProducerFibonacci;
import com.epam.preprod.task.validators.ValidatorArgs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.epam.preprod.task.Constants.ARG_FIRST_M_FIBONACCI_NUMBERS;
import static com.epam.preprod.task.Constants.ARG_FIRST_N_FIBONACCI_NUMBERS;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        execute(args);
    }

    private static void execute(String[] args) {
        try {
            int[] intValidArgs = ValidatorArgs.check(args);
            ProducerFibonacci.runProducer(intValidArgs[ARG_FIRST_N_FIBONACCI_NUMBERS]);
            ConsumerFibonacci.runConsumer(intValidArgs[ARG_FIRST_M_FIBONACCI_NUMBERS]);

            // catch all exceptions in ValidatorArgs
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
