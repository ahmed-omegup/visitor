package lib.handlers;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import port.IFactory;

public final class HandlerFactory {
    public HandlerFactory() {}

    public ArithmeticDepthHistogramBuilder arithmeticDepthHistogramBuilder() {
        return new ArithmeticDepthHistogramBuilder();
    }

    public ArithmeticOperatorLabelCollector arithmeticOperatorLabelCollector() {
        return new ArithmeticOperatorLabelCollector();
    }

    public ArityHistogramBuilder arityHistogramBuilder() {
        return new ArityHistogramBuilder();
    }

    public BinaryNodeDepthSequenceBuilder binaryNodeDepthSequenceBuilder() {
        return new BinaryNodeDepthSequenceBuilder();
    }

    public BinaryOperatorCounter binaryOperatorCounter() {
        return new BinaryOperatorCounter();
    }

    public BinaryOperatorDepthHistogramBuilder binaryOperatorDepthHistogramBuilder() {
        return new BinaryOperatorDepthHistogramBuilder();
    }

    public BinaryOperatorLabelCollector binaryOperatorLabelCollector() {
        return new BinaryOperatorLabelCollector();
    }

    public BooleanOperatorCounter booleanOperatorCounter() {
        return new BooleanOperatorCounter();
    }

    public BooleanOperatorDepthHistogramBuilder booleanOperatorDepthHistogramBuilder() {
        return new BooleanOperatorDepthHistogramBuilder();
    }

    public BooleanOperatorLabelCollector booleanOperatorLabelCollector() {
        return new BooleanOperatorLabelCollector();
    }

    public BranchingFactorHistogramBuilder branchingFactorHistogramBuilder() {
        return new BranchingFactorHistogramBuilder();
    }

    public BreadthFirstLabelPrinter breadthFirstLabelPrinter() {
        return new BreadthFirstLabelPrinter();
    }

    public CompactInfixPrinter compactInfixPrinter() {
        return new CompactInfixPrinter();
    }

    public ComparisonDepthHistogramBuilder comparisonDepthHistogramBuilder() {
        return new ComparisonDepthHistogramBuilder();
    }

    public ComparisonLabelCollector comparisonLabelCollector() {
        return new ComparisonLabelCollector();
    }

    public ComparisonOperatorCounter comparisonOperatorCounter() {
        return new ComparisonOperatorCounter();
    }

    public ConditionalBranchLabelCollector conditionalBranchLabelCollector() {
        return new ConditionalBranchLabelCollector();
    }

    public ConditionalCounter conditionalCounter() {
        return new ConditionalCounter();
    }

    public ConditionalDepthSequenceBuilder conditionalDepthSequenceBuilder() {
        return new ConditionalDepthSequenceBuilder();
    }

    public ConditionalPathCollector conditionalPathCollector() {
        return new ConditionalPathCollector();
    }

    public ConstantExpressionChecker constantExpressionChecker() {
        return new ConstantExpressionChecker();
    }

    public ConstantFolder constantFolder(IFactory factory) {
        return new ConstantFolder(factory);
    }

    public CsvNodeExporter csvNodeExporter() {
        return new CsvNodeExporter();
    }

    public DeepestNodePathFinder deepestNodePathFinder() {
        return new DeepestNodePathFinder();
    }

    public DepthAnnotatedPreorderPrinter depthAnnotatedPreorderPrinter() {
        return new DepthAnnotatedPreorderPrinter();
    }

    public DepthCalculator depthCalculator() {
        return new DepthCalculator();
    }

    public DistinctLeafLabelCollector distinctLeafLabelCollector() {
        return new DistinctLeafLabelCollector();
    }

    public DotGraphExporter dotGraphExporter() {
        return new DotGraphExporter();
    }

    public EvaluationComplexityScorer evaluationComplexityScorer() {
        return new EvaluationComplexityScorer();
    }

    public EvaluationOrderListBuilder evaluationOrderListBuilder() {
        return new EvaluationOrderListBuilder();
    }

    public ExecutionPlanBuilder executionPlanBuilder() {
        return new ExecutionPlanBuilder();
    }

    public ExpressionFingerprintReporter expressionFingerprintReporter() {
        return new ExpressionFingerprintReporter();
    }

    public ExpressionSummaryReporter expressionSummaryReporter() {
        return new ExpressionSummaryReporter();
    }

    public FunctionArgumentRootCollector functionArgumentRootCollector() {
        return new FunctionArgumentRootCollector();
    }

    public FunctionArityCollector functionArityCollector() {
        return new FunctionArityCollector();
    }

    public FunctionAritySequenceBuilder functionAritySequenceBuilder() {
        return new FunctionAritySequenceBuilder();
    }

    public FunctionCallCounter functionCallCounter() {
        return new FunctionCallCounter();
    }

    public FunctionCallDepthSequenceBuilder functionCallDepthSequenceBuilder() {
        return new FunctionCallDepthSequenceBuilder();
    }

    public FunctionCallPathCollector functionCallPathCollector() {
        return new FunctionCallPathCollector();
    }

    public FunctionCallSignatureCollector functionCallSignatureCollector() {
        return new FunctionCallSignatureCollector();
    }

    public FunctionNameCollector functionNameCollector() {
        return new FunctionNameCollector();
    }

    public HtmlExpressionExporter htmlExpressionExporter() {
        return new HtmlExpressionExporter();
    }

    public IndentedTracePrinter indentedTracePrinter() {
        return new IndentedTracePrinter();
    }

    public IntegerEvaluator integerEvaluator() {
        return new IntegerEvaluator();
    }

    public IntegerEvaluator integerEvaluator(Map<String, Integer> variables, Map<String, Function<List<Integer>, Integer>> functions) {
        return new IntegerEvaluator(variables, functions);
    }

    public JavaLikeExpressionEmitter javaLikeExpressionEmitter() {
        return new JavaLikeExpressionEmitter();
    }

    public JsonExporter jsonExporter() {
        return new JsonExporter();
    }

    public LeafCounter leafCounter() {
        return new LeafCounter();
    }

    public LeafDepthHistogramBuilder leafDepthHistogramBuilder() {
        return new LeafDepthHistogramBuilder();
    }

    public LeafDepthSequenceBuilder leafDepthSequenceBuilder() {
        return new LeafDepthSequenceBuilder();
    }

    public LeafLabelSequenceBuilder leafLabelSequenceBuilder() {
        return new LeafLabelSequenceBuilder();
    }

    public LeafPathCollector leafPathCollector() {
        return new LeafPathCollector();
    }

    public LevelGroupedLabelCollector levelGroupedLabelCollector() {
        return new LevelGroupedLabelCollector();
    }

    public LevelWidthHistogramBuilder levelWidthHistogramBuilder() {
        return new LevelWidthHistogramBuilder();
    }

    public LiteralCollector literalCollector() {
        return new LiteralCollector();
    }

    public LiteralDepthHistogramBuilder literalDepthHistogramBuilder() {
        return new LiteralDepthHistogramBuilder();
    }

    public LiteralDepthSequenceBuilder literalDepthSequenceBuilder() {
        return new LiteralDepthSequenceBuilder();
    }

    public LiteralFrequencyBuilder literalFrequencyBuilder() {
        return new LiteralFrequencyBuilder();
    }

    public LiteralLengthHistogramBuilder literalLengthHistogramBuilder() {
        return new LiteralLengthHistogramBuilder();
    }

    public LiteralPathCollector literalPathCollector() {
        return new LiteralPathCollector();
    }

    public Object literalValueExtractor() {
        return new LiteralValueExtractor();
    }

    public LongestLiteralFinder longestLiteralFinder() {
        return new LongestLiteralFinder();
    }

    public LongestVariableNameFinder longestVariableNameFinder() {
        return new LongestVariableNameFinder();
    }

    public MarkdownOutlineExporter markdownOutlineExporter() {
        return new MarkdownOutlineExporter();
    }

    public MaxFunctionArityFinder maxFunctionArityFinder() {
        return new MaxFunctionArityFinder();
    }

    public MaximumBranchingFactorFinder maximumBranchingFactorFinder() {
        return new MaximumBranchingFactorFinder();
    }

    public MermaidFlowchartExporter mermaidFlowchartExporter() {
        return new MermaidFlowchartExporter();
    }

    public MermaidMindmapExporter mermaidMindmapExporter() {
        return new MermaidMindmapExporter();
    }

    public NodeCounter nodeCounter() {
        return new NodeCounter();
    }

    public NodeHistogramBuilder nodeHistogramBuilder() {
        return new NodeHistogramBuilder();
    }

    public NodePathCollector nodePathCollector() {
        return new NodePathCollector();
    }

    public NodeTypeCollector nodeTypeCollector() {
        return new NodeTypeCollector();
    }

    public NonLeafDepthSequenceBuilder nonLeafDepthSequenceBuilder() {
        return new NonLeafDepthSequenceBuilder();
    }

    public OperatorHistogramBuilder operatorHistogramBuilder() {
        return new OperatorHistogramBuilder();
    }

    public OperatorSequenceCollector operatorSequenceCollector() {
        return new OperatorSequenceCollector();
    }

    public ParenthesizedInfixPrinter parenthesizedInfixPrinter() {
        return new ParenthesizedInfixPrinter();
    }

    public PathAnnotatedOutlineExporter pathAnnotatedOutlineExporter() {
        return new PathAnnotatedOutlineExporter();
    }

    public PostOrderLabelPrinter postOrderLabelPrinter() {
        return new PostOrderLabelPrinter();
    }

    public PrefixNotationPrinter prefixNotationPrinter() {
        return new PrefixNotationPrinter();
    }

    public ReversePolishPrinter reversePolishPrinter() {
        return new ReversePolishPrinter();
    }

    public RootToLeafTracePrinter rootToLeafTracePrinter() {
        return new RootToLeafTracePrinter();
    }

    public SExpressionExporter sExpressionExporter() {
        return new SExpressionExporter();
    }

    public ShallowestLeafPathFinder shallowestLeafPathFinder() {
        return new ShallowestLeafPathFinder();
    }

    public SideEffectFreeChecker sideEffectFreeChecker() {
        return new SideEffectFreeChecker();
    }

    public StructuralHashBuilder structuralHashBuilder() {
        return new StructuralHashBuilder();
    }

    public StructuralSignatureBuilder structuralSignatureBuilder() {
        return new StructuralSignatureBuilder();
    }

    public SubexpressionListingBuilder subexpressionListingBuilder() {
        return new SubexpressionListingBuilder();
    }

    public TreeDiagramPrinter treeDiagramPrinter() {
        return new TreeDiagramPrinter();
    }

    public TsvNodeExporter tsvNodeExporter() {
        return new TsvNodeExporter();
    }

    public UnaryNodeDepthSequenceBuilder unaryNodeDepthSequenceBuilder() {
        return new UnaryNodeDepthSequenceBuilder();
    }

    public UnaryOperatorCounter unaryOperatorCounter() {
        return new UnaryOperatorCounter();
    }

    public UnaryOperatorDepthHistogramBuilder unaryOperatorDepthHistogramBuilder() {
        return new UnaryOperatorDepthHistogramBuilder();
    }

    public UnaryOperatorLabelCollector unaryOperatorLabelCollector() {
        return new UnaryOperatorLabelCollector();
    }

    public VariableCollector variableCollector() {
        return new VariableCollector();
    }

    public VariableDepthHistogramBuilder variableDepthHistogramBuilder() {
        return new VariableDepthHistogramBuilder();
    }

    public VariableDepthSequenceBuilder variableDepthSequenceBuilder() {
        return new VariableDepthSequenceBuilder();
    }

    public VariableNameLengthHistogramBuilder variableNameLengthHistogramBuilder() {
        return new VariableNameLengthHistogramBuilder();
    }

    public VariablePathCollector variablePathCollector() {
        return new VariablePathCollector();
    }

    public Object variableReferenceExtractor() {
        return new VariableReferenceExtractor();
    }

    public VariableUsageCounter variableUsageCounter() {
        return new VariableUsageCounter();
    }

    public XmlExporter xmlExporter() {
        return new XmlExporter();
    }

    public YamlExpressionExporter yamlExpressionExporter() {
        return new YamlExpressionExporter();
    }

    public ZeroDivisionRiskDetector zeroDivisionRiskDetector() {
        return new ZeroDivisionRiskDetector();
    }
}
