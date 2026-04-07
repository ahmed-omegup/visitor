package lib.expressions;

import java.util.function.Function;

import lib.expression.*;

public class CLikeBindingPowers extends Expressions<BindingPower> {

    public CLikeBindingPowers() {
        literal = new BindingPower(100, false);
        variableReference = new BindingPower(100, false);
        addition = new BindingPower(10, false);
        subtraction = new BindingPower(10, false);
        multiplication = new BindingPower(20, false);
        division = new BindingPower(20, false);
        negation = new BindingPower(30, true);
        modulo = new BindingPower(20, false);
        exponentiation = new BindingPower(40, true);
        equality = new BindingPower(5, false);
        inequality = new BindingPower(5, false);
        lessThan = new BindingPower(5, false);
        greaterThan = new BindingPower(5, false);
        lessThanOrEqual = new BindingPower(5, false);
        greaterThanOrEqual = new BindingPower(5, false);
        conjunction = new BindingPower(3, false);
        disjunction = new BindingPower(2, false);
        logicalNot = new BindingPower(30, true);
        conditional = new BindingPower(1, true);
        functionCall = new BindingPower(50, false);
    }
}