package ru.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class BoxTest {

    @Test
    void whatsThis() {
        Box sphereBox = new Box(0, 5);
        Box tetrahedronBox = new Box(4, 5);
        String sphereType = sphereBox.whatsThis();
        String tetrahedronType = tetrahedronBox.whatsThis();
        assertThat(sphereType).isEqualTo("Sphere");
        assertThat(tetrahedronType).isEqualTo("Tetrahedron");
    }

    @Test
    void whatsThisUnknown() {
        Box unknownBox = new Box(10, 5);
        String unknownType = unknownBox.whatsThis();
        assertThat(unknownType).isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVertices() {
        Box cubeBox = new Box(8, 5);
        Box unknownBox = new Box(10, -5);
        int cubeVertices = cubeBox.getNumberOfVertices();
        int unknownVertices = unknownBox.getNumberOfVertices();
        assertThat(cubeVertices).isEqualTo(8);
        assertThat(unknownVertices).isEqualTo(-1);
    }

    @Test
    void getNumberOfVerticesInvalidTest() {
        Box invalidBox = new Box(10, 0);
        int invalidVertices = invalidBox.getNumberOfVertices();
        assertThat(invalidVertices).isEqualTo(-1);
    }

    @Test
    void isExistTest() {
        Box validBox = new Box(4, 5);
        Box invalidBox = new Box(10, 0);
        boolean isValid = validBox.isExist();
        boolean isInvalid = invalidBox.isExist();
        assertThat(isValid).isTrue();
        assertThat(isInvalid).isFalse();
    }

    @Test
    void isExistNegative() {
        Box negativeBox = new Box(-1, 5);
        boolean isNegative = negativeBox.isExist();
        assertThat(isNegative).isFalse();
    }

    @Test
    void getArea() {
        Box sphereBox = new Box(0, 5);
        Box tetrahedronBox = new Box(4, 5);
        double sphereArea = sphereBox.getArea();
        double tetrahedronArea = tetrahedronBox.getArea();
        assertThat(sphereArea).isCloseTo(314.159, within(0.001));
        assertThat(tetrahedronArea).isCloseTo(43.301, within(0.001));
    }

    @Test
    void getAreaInvalid() {
        Box invalidBox = new Box(10, 5);
        double invalidArea = invalidBox.getArea();
        assertThat(invalidArea).isEqualTo(0);
    }
}
