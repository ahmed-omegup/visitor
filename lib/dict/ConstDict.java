package lib.dict;

public class ConstDict<T> extends Dict<T> {

    public ConstDict(T initial) {
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