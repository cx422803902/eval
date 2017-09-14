import org.goiot.utils.eval.functor.FunctionBase;

/**
 * Created by chenxing on 2017/9/14.
 */
public class TestFun extends FunctionBase{


    public TestFun() {
        super("mins", 1, true);
    }

    @Override
    public Object operate(Object[] operands) {
        Number min = ((Number) operands[0]).doubleValue();
        for(int i = 1; i < operands.length; i++) {
            min = Math.min(min.doubleValue(), ((Number) operands[i]).doubleValue());
        }
        return min;
    }

}
