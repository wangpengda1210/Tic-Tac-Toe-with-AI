class Calculator {
    private CalculatorEngine engine;

    public Calculator(CalculatorEngine engine) {
        this.engine = engine;
    }

    public String divide(int a, int b) {
        // Implement me using engine field
        try {
            return "Division of " + a + " by " + b + " = " + engine.divide(a, b);
        } catch (ArithmeticException e) {
            return "Division by zero is prohibited";
        }
    }
}