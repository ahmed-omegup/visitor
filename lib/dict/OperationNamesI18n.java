package lib.dict;

import java.util.LinkedHashMap;
import java.util.Map;

public final class OperationNamesI18n {
    private static final Map<String, Dict<String>> OPERATION_NAMES_BY_LANGUAGE = createOperationNamesByLanguage();

    private OperationNamesI18n() {
    }

    public static Map<String, Dict<String>> operationNamesByLanguage() {
        return OPERATION_NAMES_BY_LANGUAGE;
    }

    private static Map<String, Dict<String>> createOperationNamesByLanguage() {
        var languages = new LinkedHashMap<String, Dict<String>>();
        languages.put("en", english());
        languages.put("es", spanish());
        languages.put("fr", french());
        languages.put("de", german());
        languages.put("it", italian());
        languages.put("pt", portuguese());
        return Map.copyOf(languages);
    }

    private static Dict<String> english() {
        var names = new Dict<String>();
        names.literal = "literal";
        names.variableReference = "variableReference";
        names.addition = "addition";
        names.subtraction = "subtraction";
        names.multiplication = "multiplication";
        names.division = "division";
        names.negation = "negation";
        names.modulo = "modulo";
        names.exponentiation = "exponentiation";
        names.equality = "equality";
        names.inequality = "inequality";
        names.lessThan = "lessThan";
        names.greaterThan = "greaterThan";
        names.lessThanOrEqual = "lessThanOrEqual";
        names.greaterThanOrEqual = "greaterThanOrEqual";
        names.conjunction = "conjunction";
        names.disjunction = "disjunction";
        names.logicalNot = "logicalNot";
        names.conditional = "conditional";
        names.functionCall = "functionCall";
        return names;
    }

    private static Dict<String> spanish() {
        var names = new Dict<String>();
        names.literal = "literal";
        names.variableReference = "referenciaVariable";
        names.addition = "suma";
        names.subtraction = "resta";
        names.multiplication = "multiplicacion";
        names.division = "division";
        names.negation = "negacion";
        names.modulo = "modulo";
        names.exponentiation = "potencia";
        names.equality = "igualdad";
        names.inequality = "desigualdad";
        names.lessThan = "menorQue";
        names.greaterThan = "mayorQue";
        names.lessThanOrEqual = "menorOIgual";
        names.greaterThanOrEqual = "mayorOIgual";
        names.conjunction = "conjuncion";
        names.disjunction = "disyuncion";
        names.logicalNot = "negacionLogica";
        names.conditional = "condicional";
        names.functionCall = "llamadaFuncion";
        return names;
    }

    private static Dict<String> french() {
        var names = new Dict<String>();
        names.literal = "litteral";
        names.variableReference = "referenceVariable";
        names.addition = "addition";
        names.subtraction = "soustraction";
        names.multiplication = "multiplication";
        names.division = "division";
        names.negation = "negation";
        names.modulo = "modulo";
        names.exponentiation = "exponentiation";
        names.equality = "egalite";
        names.inequality = "inegalite";
        names.lessThan = "inferieurA";
        names.greaterThan = "superieurA";
        names.lessThanOrEqual = "inferieurOuEgal";
        names.greaterThanOrEqual = "superieurOuEgal";
        names.conjunction = "conjonction";
        names.disjunction = "disjonction";
        names.logicalNot = "nonLogique";
        names.conditional = "conditionnel";
        names.functionCall = "appelDeFonction";
        return names;
    }

    private static Dict<String> german() {
        var names = new Dict<String>();
        names.literal = "literal";
        names.variableReference = "variablenReferenz";
        names.addition = "addition";
        names.subtraction = "subtraktion";
        names.multiplication = "multiplikation";
        names.division = "division";
        names.negation = "negation";
        names.modulo = "modulo";
        names.exponentiation = "potenzierung";
        names.equality = "gleichheit";
        names.inequality = "ungleichheit";
        names.lessThan = "kleinerAls";
        names.greaterThan = "groesserAls";
        names.lessThanOrEqual = "kleinerOderGleich";
        names.greaterThanOrEqual = "groesserOderGleich";
        names.conjunction = "konjunktion";
        names.disjunction = "disjunktion";
        names.logicalNot = "logischesNicht";
        names.conditional = "bedingung";
        names.functionCall = "funktionsAufruf";
        return names;
    }

    private static Dict<String> italian() {
        var names = new Dict<String>();
        names.literal = "letterale";
        names.variableReference = "riferimentoVariabile";
        names.addition = "addizione";
        names.subtraction = "sottrazione";
        names.multiplication = "moltiplicazione";
        names.division = "divisione";
        names.negation = "negazione";
        names.modulo = "modulo";
        names.exponentiation = "esponenziazione";
        names.equality = "uguaglianza";
        names.inequality = "disuguaglianza";
        names.lessThan = "minoreDi";
        names.greaterThan = "maggioreDi";
        names.lessThanOrEqual = "minoreOUguale";
        names.greaterThanOrEqual = "maggioreOUguale";
        names.conjunction = "congiunzione";
        names.disjunction = "disgiunzione";
        names.logicalNot = "nonLogico";
        names.conditional = "condizionale";
        names.functionCall = "chiamataFunzione";
        return names;
    }

    private static Dict<String> portuguese() {
        var names = new Dict<String>();
        names.literal = "literal";
        names.variableReference = "referenciaVariavel";
        names.addition = "adicao";
        names.subtraction = "subtracao";
        names.multiplication = "multiplicacao";
        names.division = "divisao";
        names.negation = "negacao";
        names.modulo = "modulo";
        names.exponentiation = "exponenciacao";
        names.equality = "igualdade";
        names.inequality = "desigualdade";
        names.lessThan = "menorQue";
        names.greaterThan = "maiorQue";
        names.lessThanOrEqual = "menorOuIgual";
        names.greaterThanOrEqual = "maiorOuIgual";
        names.conjunction = "conjuncao";
        names.disjunction = "disjuncao";
        names.logicalNot = "naoLogico";
        names.conditional = "condicional";
        names.functionCall = "chamadaFuncao";
        return names;
    }
}