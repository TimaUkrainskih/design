package ru.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        input.add(2);
        Predicate<Integer> even = n -> n % 2 == 0;
        ListUtils.removeIf(input, even);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveIfNotElementsRemoved() {
        Predicate<Integer> even = n -> n % 2 == 0;
        ListUtils.removeIf(input, even);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIf() {
        input.add(2);
        Predicate<Integer> even = n -> n % 2 == 0;
        ListUtils.replaceIf(input, even, 999);
        assertThat(input).hasSize(3).containsSequence(1, 3, 999);
    }

    @Test
    void whenReplaceIfNotElementsReplaced() {
        input.add(2);
        Predicate<Integer> even = n -> n % 2 == 0;
        ListUtils.replaceIf(input, even, 999);
        assertThat(input).hasSize(3).containsSequence(1, 3, 999);
    }

    @Test
    void whenRemoveAll() {
        input.add(2);
        ListUtils.removeAll(input, List.of(3,1,2));
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveAllPartial() {
        input.add(2);
        ListUtils.removeAll(input, List.of(2));
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }
}