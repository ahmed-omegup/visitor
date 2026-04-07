package lib.visitors;

import lib.expression.*;
import lib.expression.category.*;


public class CategoryVisitor<E> implements ExpressionVisitor<CategoryExpression<E>, E> {
    @Override
    public CategoryExpression<E> visit(Literal<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new LeafExpression<E>() {
                    @Override
                    public <R> R accept(LeafExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }
    
    @Override
    public CategoryExpression<E> visit(VariableReference<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new LeafExpression<E>() {
                    @Override
                    public <R> R accept(LeafExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(FunctionCall<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(e);
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Negation<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ArithmeticExpression<E>() {
                    @Override
                    public <R> R accept(ArithmeticExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Conditional<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new LogicalExpression<E>() {
                    @Override
                    public <R> R accept(LogicalExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(LogicalNot<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new LogicalExpression<E>() {
                    @Override
                    public <R> R accept(LogicalExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Addition<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ArithmeticExpression<E>() { 
                    @Override
                    public <R> R accept(ArithmeticExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Subtraction<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ArithmeticExpression<E>() {
                    @Override
                    public <R> R accept(ArithmeticExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Multiplication<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ArithmeticExpression<E>() {
                    @Override
                    public <R> R accept(ArithmeticExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Division<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ArithmeticExpression<E>() {
                    @Override
                    public <R> R accept(ArithmeticExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Modulo<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ArithmeticExpression<E>() {
                    @Override
                    public <R> R accept(ArithmeticExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Exponentiation<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ArithmeticExpression<E>() {
                    @Override
                    public <R> R accept(ArithmeticExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Equality<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ComparisonExpression<E>() {
                    @Override
                    public <R> R accept(ComparisonExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Inequality<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ComparisonExpression<E>() {
                    @Override
                    public <R> R accept(ComparisonExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(LessThan<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ComparisonExpression<E>() {
                    @Override
                    public <R> R accept(ComparisonExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(GreaterThan<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ComparisonExpression<E>() {
                    @Override
                    public <R> R accept(ComparisonExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(LessThanOrEqual<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ComparisonExpression<E>() {
                    @Override
                    public <R> R accept(ComparisonExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(GreaterThanOrEqual<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new ComparisonExpression<E>() {
                    @Override
                    public <R> R accept(ComparisonExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Conjunction<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new LogicalExpression<E>() {
                    @Override
                    public <R> R accept(LogicalExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression<E> visit(Disjunction<E> e) {
        return new CategoryExpression<E>() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R, E> visitor) {
                return visitor.visit(new LogicalExpression<E>() {
                    @Override
                    public <R> R accept(LogicalExpressionVisitor<R, E> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

}