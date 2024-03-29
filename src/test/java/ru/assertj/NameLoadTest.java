package ru.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void parseEmptyStringThrowsException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("Names array is empty");
    }

    @Test
    void validateThrowsExceptionIfNameDoesNotContainEqualsSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "KhalidChauhan";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("this name: %s does not contain the symbol '='".formatted(name));
    }

    @Test
    void validateThrowsExceptionIfNameStartsWithEqualsSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "=MaryamAkhter";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("this name: %s does not contain a key".formatted(name));
    }

    @Test
    void validateThrowsExceptionIfNameDoesNotContainValueAfterEqualsSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "MaryamAkhter=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isEqualTo("this name: %s does not contain a value".formatted(name));
    }

    @Test
    void getMapEmptyMapThrowsException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .message()
                .isEqualTo("collection contains no data");
    }
}