package port;

import java.util.function.Function;

public interface IHandlerFactory1<E> extends IHandlerFactory<E> {

    Function<E, String> cLikeSyntaxPrinter();

    Function<E, String> i18nDict(String lang);
}