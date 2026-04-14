package spec.visitors;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import testsupport.HandlerTestFixtures;

public final class TestVariants {
    private TestVariants() {
    }

    public static Stream<Arguments> all() {
        return Stream.of(
            Arguments.of("v1", new TestSupport<>(HandlerTestFixtures.v1Handler())),
            Arguments.of("v2", new TestSupport<>(HandlerTestFixtures.v2Handler())),
            Arguments.of("v2-support2", new TestSupport2<>(HandlerTestFixtures.v2Handler()))
        );
    }
}