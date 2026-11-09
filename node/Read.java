package node;

import java.util.Scanner;
import eval.Environment;
import eval.EvalException;

public class Read extends Node{
    Fact id;
    private static final Scanner scanner = new Scanner(System.in);

    public Read(Fact id) {
        this.id = id;
    }

    @Override
    public double eval(Environment env) throws EvalException {
        if (!scanner.hasNextDouble()) {
            throw new EvalException(position, "Expected double input, no double found.");
        }
        double value = scanner.nextDouble();
        env.put(id.getLexeme(), value);
        return value;
    }
}
