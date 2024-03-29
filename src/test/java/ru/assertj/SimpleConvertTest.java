package ru.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("LaVV4AG5Xn", "o7zHlbDm3K", "H7KF0Vh6fz", "ZTerejX", "sap6kJS");
        assertThat(list).hasSize(5)
                .contains("o7zHlbDm3K")
                .contains("ZTerejX")
                .contains("o7zHlbDm3K", Index.atIndex(1))
                .containsAnyOf("LaVV4AG5Xn", "H7KF0Vh6fz", "sap6kJS")
                .doesNotContain("sap6kJS", Index.atIndex(5));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("B0PUbXxoyV", "5qzzFzRnJ", "tKpKeiH", "GSXHL1cJ9d1");
        assertThat(set).hasSize(4)
                .contains("tKpKeiH")
                .contains("GSXHL1cJ9d1")
                .containsAnyOf("B0PUbXxoyV", "5qzzFzRnJ", "tKpKeiH")
                .doesNotContain("yXdnTrH", "EuGcRPQBwg", "QgwHpCz");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("a9VhhJ6myh7", "khjydi2p2", "BdMDJrLvWvs");
        assertThat(map).hasSize(3)
                .containsEntry("a9VhhJ6myh7", 0)
                .containsEntry("khjydi2p2", 1)
                .containsEntry("BdMDJrLvWvs", 2);
    }
}