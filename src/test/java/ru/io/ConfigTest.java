package ru.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan Ivanov");
        assertThat(config.value("age")).isEqualTo("30=1");
        assertThat(config.value("lastname")).isEqualTo("Ivanov=");
    }

    @Test
    void whenPairWithCommentsAndEmptyLines() {
        String path = "./data/pair_with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Ivan Ivanov");
        assertThat(config.value("age")).isEqualTo("30");
    }

    @Test
    void whenInvalidPairFormat() {
        String path = "./data/invalid_pair_format.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid format");
    }
}