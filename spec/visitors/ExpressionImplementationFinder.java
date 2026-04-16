package spec.visitors;

import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lib.expression.Expression;

@SuppressWarnings({"rawtypes", "unchecked"})
final class ExpressionImplementationFinder {
    private ExpressionImplementationFinder() {
    }

    static List<Class<? extends Expression>> findExpressionImplementations() throws Exception {
        var implementations = new ArrayList<Class<? extends Expression>>();

        for (var className : classNamesOnClasspath()) {
            var candidate = Class.forName(className);

            if (!Expression.class.isAssignableFrom(candidate)) {
                continue;
            }

            if (candidate.isInterface() || Modifier.isAbstract(candidate.getModifiers())) {
                continue;
            }

            implementations.add((Class<? extends Expression>) candidate);
        }

        implementations.sort(Comparator.comparing(Class::getName));
        return implementations;
    }

    private static List<String> classNamesOnClasspath() throws Exception {
        var classNames = new ArrayList<String>();

        for (var root : classpathRoots()) {
            try (var paths = Files.walk(root)) {
                paths
                    .filter(path -> path.getFileName().toString().endsWith(".class"))
                    .map(path -> classNameFor(root, path))
                    .forEach(classNames::add);
            }
        }

        classNames.sort(String::compareTo);
        return classNames;
    }

    private static Set<Path> classpathRoots() {
        var roots = new LinkedHashSet<Path>();
        roots.add(codeSourceRoot(Expression.class));
        roots.add(codeSourceRoot(ExpressionImplementationFinder.class));
        return roots;
    }

    private static Path codeSourceRoot(Class<?> type) {
        try {
            var location = type.getProtectionDomain().getCodeSource().getLocation();
            var root = Path.of(location.toURI());
            if (!Files.isDirectory(root)) {
                throw new IllegalStateException("Expected a directory code source for " + type.getName());
            }
            return root;
        } catch (URISyntaxException exception) {
            throw new IllegalStateException("Unable to resolve code source for " + type.getName(), exception);
        }
    }

    private static String classNameFor(Path root, Path classFile) {
        var relativePath = root.relativize(classFile).toString();
        return stripClassSuffix(relativePath).replace('/', '.').replace('\\', '.');
    }

    private static String stripClassSuffix(String fileName) {
        return fileName.substring(0, fileName.length() - ".class".length());
    }
}