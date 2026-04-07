package lib.expressions;

public class ExpressionClassNames extends Expressions<String> {

    public ExpressionClassNames() {
        literal = "Literal";
        variableReference = "VariableReference";
        addition = "Addition";
        subtraction = "Subtraction";
        multiplication = "Multiplication";
        division = "Division";
        negation = "Negation";
        modulo = "Modulo";
        exponentiation = "Exponentiation";
        equality = "Equality";
        inequality = "Inequality";
        lessThan = "LessThan";
        greaterThan = "GreaterThan";
        lessThanOrEqual = "LessThanOrEqual";
        greaterThanOrEqual = "GreaterThanOrEqual";
        conjunction = "Conjunction";
        disjunction = "Disjunction";
        logicalNot = "LogicalNot";
        conditional = "Conditional";
        functionCall = "FunctionCall";
    }
}