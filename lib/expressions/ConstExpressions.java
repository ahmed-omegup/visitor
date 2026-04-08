package lib.expressions;

public class ConstExpressions<T> extends Expressions<T> {

    public ConstExpressions(T initial) {
        literal = initial;
        variableReference = initial;
        addition = initial;
        subtraction = initial;
        multiplication = initial;
        division = initial;
        negation = initial;
        modulo = initial;
        exponentiation = initial;
        equality = initial;
        inequality = initial;
        lessThan = initial;
        greaterThan = initial;
        lessThanOrEqual = initial;
        greaterThanOrEqual = initial;
        conjunction = initial;
        disjunction = initial;
        logicalNot = initial;
        conditional = initial;
        functionCall = initial;
    }
}