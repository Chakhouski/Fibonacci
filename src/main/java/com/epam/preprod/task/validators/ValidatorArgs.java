package com.epam.preprod.task.validators;

import static com.epam.preprod.task.Constants.*;

public class ValidatorArgs {

    private ValidatorArgs() {
    }

    public static int[] check(String[] args) {
        // check min quantity args
        if (args == null || args.length < MIN_QUANTITY_COMMAND_LINE_ARGS) {
            throw new IllegalArgumentException(EXCEPTION_WRONG_COMMAND_LINE_ARGS);
        }

        // is parse to int
        int n = Integer.parseInt(args[ARG_FIRST_N_FIBONACCI_NUMBERS]);
        int m = Integer.parseInt(args[ARG_FIRST_M_FIBONACCI_NUMBERS]);

        // check range of each arg (default 1-90)
        checkRangeArg(n);
        checkRangeArg(m);

        // check n at least m
        if (n < m) {
            throw new IllegalArgumentException(String.format(EXCEPTION_N_LESS_M, n, m));
        }
        return new int[]{n, m};
    }

    private static void checkRangeArg(int arg) {
        if (arg < MIN_QUANTITY_NUMBERS || arg > MAX_QUANTITY_NUMBERS) {
            throw new IllegalArgumentException(String.format(EXCEPTION_OUT_OF_RANGE,
                    MIN_QUANTITY_NUMBERS, MAX_QUANTITY_NUMBERS, arg));
        }
    }
}
