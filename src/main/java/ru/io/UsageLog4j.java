package ru.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        long value0 = 100000000000L;
        int value1 = 105;
        short value2 = 35;
        byte value3 = 40;
        double value4 = 150.0000012131;
        float value5 = 134.0042040204204F;
        boolean value6 = true;
        char value7 = 'a';
        LOG.info("Info: long: {}, int: {}, short: {}, byte: {}," +
                " double: {}, float: {}, boolean: {}, char: {}",
                value0, value1, value2, value3, value4, value5, value6, value7);
    }
}