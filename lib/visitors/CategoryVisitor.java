package lib.visitors;

import lib.expression.*;
import lib.expression.category.*;


public class CategoryVisitor implements ExpressionVisitor<CategoryExpression> {
    @Override
    public CategoryExpression visit(Literal e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new LeafExpression() {
                    @Override
                    public <R> R accept(LeafExpressionVisitor<R> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }
    
    @Override
    public CategoryExpression visit(VariableReference e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new LeafExpression() {
                    @Override
                    public <R> R accept(LeafExpressionVisitor<R> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(FunctionCall e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(e);
            }
        };
    }

    @Override
    public CategoryExpression visit(Negation e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new OddExpression() {
                    @Override
                    public <R> R accept(OddExpressionVisitor<R> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Conditional e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new OddExpression() {
                    @Override
                    public <R> R accept(OddExpressionVisitor<R> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(LogicalNot e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new OddExpression() {
                    @Override
                    public <R> R accept(OddExpressionVisitor<R> visitor) {
                        return visitor.visit(e);
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Addition e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new BinaryArithmeticExpression() { 
                            @Override
                            public <R> R accept(BinaryArithmeticExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Subtraction e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new BinaryArithmeticExpression() {
                            @Override
                            public <R> R accept(BinaryArithmeticExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Multiplication e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new BinaryArithmeticExpression() {
                            @Override
                            public <R> R accept(BinaryArithmeticExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Division e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new BinaryArithmeticExpression() {
                            @Override
                            public <R> R accept(BinaryArithmeticExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Modulo e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new BinaryArithmeticExpression() {
                            @Override
                            public <R> R accept(BinaryArithmeticExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Exponentiation e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new BinaryArithmeticExpression() {
                            @Override
                            public <R> R accept(BinaryArithmeticExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Equality e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new ComparisonExpression() {
                            @Override
                            public <R> R accept(ComparisonExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Inequality e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new ComparisonExpression() {
                            @Override
                            public <R> R accept(ComparisonExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(LessThan e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new ComparisonExpression() {
                            @Override
                            public <R> R accept(ComparisonExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(GreaterThan e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new ComparisonExpression() {
                            @Override
                            public <R> R accept(ComparisonExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(LessThanOrEqual e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new ComparisonExpression() {
                            @Override
                            public <R> R accept(ComparisonExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(GreaterThanOrEqual e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new ComparisonExpression() {  
                            @Override
                            public <R> R accept(ComparisonExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Conjunction e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new LogicalExpression() {
                            @Override
                            public <R> R accept(LogicalExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public CategoryExpression visit(Disjunction e) {
        return new CategoryExpression() {
            @Override
            public <R> R accept(CategoryExpressionVisitor<R> visitor) {
                return visitor.visit(new BinaryExpression() {
                    @Override
                    public <R> R accept(BinaryExpressionVisitor<R> visitor) {
                        return visitor.visit(new LogicalExpression() {
                            @Override
                            public <R> R accept(LogicalExpressionVisitor<R> visitor) {
                                return visitor.visit(e);
                            }
                        });
                    }
                });
            }
        };
    }

}