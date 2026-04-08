package spec.visitors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import lib.expression.ExpressionV1;
import lib.handlers.HandlerFactory;
import port.IExpressionFactory;

class OperationNamesI18nTest {
    private final HandlerFactory handler = new HandlerFactory();
    private final IExpressionFactory<ExpressionV1> factory = handler.expressionFactory();

    @Test
    void providesI18nDictionariesForSeveralLanguages() {
        assertNotNull(handler.i18nDict("en"));
        assertNotNull(handler.i18nDict("es"));
        assertNotNull(handler.i18nDict("fr"));
        assertNotNull(handler.i18nDict("de"));
        assertNotNull(handler.i18nDict("it"));
        assertNotNull(handler.i18nDict("pt"));
    }

    @Test
    void exposesTranslatedOperationNamesThroughHandler() {
        var english = handler.i18nDict("en");
        var spanish = handler.i18nDict("es");

        assertEquals("addition", english.apply(factory.addition(factory.literal("1"), factory.literal("2"))));
        assertEquals("functionCall", english.apply(factory.functionCall(factory.variableReference("f"), java.util.List.of(factory.literal("1")))));
        assertEquals("suma", spanish.apply(factory.addition(factory.literal("1"), factory.literal("2"))));
        assertEquals("llamadaFuncion", spanish.apply(factory.functionCall(factory.variableReference("f"), java.util.List.of(factory.literal("1")))));
        assertEquals("negacionLogica", spanish.apply(factory.logicalNot(factory.variableReference("x"))));
    }

    @Test
    void returnsNullForUnknownLanguage() {
        assertNull(handler.i18nDict("jp"));
    }
}