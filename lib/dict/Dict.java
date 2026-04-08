package lib.dict;

import port.IExpressionDict;

public class Dict<T> implements IExpressionDict<T> {
    public T literal;
    public T variableReference;
    public T addition;
    public T subtraction;
    public T multiplication;
    public T division;
    public T negation;
    public T modulo;
    public T exponentiation;
    public T equality;
    public T inequality;
    public T lessThan;
    public T greaterThan;
    public T lessThanOrEqual;
    public T greaterThanOrEqual;
    public T conjunction;
    public T disjunction;
    public T logicalNot;
    public T conditional;
    public T functionCall;

    @Override
    public T literal() { return literal; }
    @Override
    public T variableReference() { return variableReference; }
    @Override
    public T addition() { return addition; }
    @Override
    public T subtraction() { return subtraction; }
    @Override
    public T multiplication() { return multiplication; }
    @Override
    public T division() { return division; }
    @Override
    public T negation() { return negation; }
    @Override
    public T modulo() { return modulo; }
    @Override
    public T exponentiation() { return exponentiation; }
    @Override
    public T equality() { return equality; }
    @Override
    public T inequality() { return inequality; }
    @Override
    public T lessThan() { return lessThan; }
    @Override
    public T greaterThan() { return greaterThan; }
    @Override
    public T lessThanOrEqual() { return lessThanOrEqual; }
    @Override
    public T greaterThanOrEqual() { return greaterThanOrEqual; }
    @Override
    public T conjunction() { return conjunction; }
    @Override
    public T disjunction() { return disjunction; }
    @Override
    public T logicalNot() { return logicalNot; }
    @Override
    public T conditional() { return conditional; }
    @Override
    public T functionCall() { return functionCall; }
}