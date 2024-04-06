package ru.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int sizeInput = 0;
    private int sizeOutput = 0;

    public T poll() {
        if (sizeOutput == 0 && sizeInput == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (sizeOutput == 0) {
            while (sizeInput > 0) {
                output.push(input.pop());
                sizeInput--;
                sizeOutput++;
            }
        }
        sizeOutput--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        sizeInput++;
    }

}