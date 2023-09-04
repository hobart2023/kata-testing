import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

class Computer {
    private final Map<Enum<Operation>, BinaryOperator<Integer>> map = new HashMap<>();

    public Computer() {
        map.put(Operation.SUM, Integer::sum);
        map.put(Operation.DIFF, (num1, num2) -> num1 - num2);
        map.put(Operation.MULTI, (num1, num2) -> num1 * num2);
        map.put(Operation.DIV, (num1, num2) -> num1 / num2);
    }

    public int compute(Enum<Operation> operation, int a, int b) {
        return map.get(operation).apply(a, b);
    }
}
