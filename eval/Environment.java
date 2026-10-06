package eval;
import java.util.HashMap;
import java.util.Map;
/**
 * A referencing environment for bindings
 */
public class Environment {
    
    protected HashMap<String, Double> envHashMap = new HashMap<>();

    /**
     * @param var
     * @param val
     * @return
     */
    public double put(String var, double val) {
      	envHashMap.put(var, val);
        return val;
    }

    /**
     * @param pos
     * @param var
     * @return
     * @throws EvalException
     */
    public double get(int pos, String var) throws EvalException {
    	Double value = envHashMap.get(var);
        if (value == null) {
            throw new EvalException(pos, var);
        }
        return value;
    }
}