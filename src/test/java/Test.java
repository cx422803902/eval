import org.goiot.utils.eval.TExprParams;
import org.goiot.utils.eval.TExprParser;
import org.goiot.utils.eval.TableManager;

public class Test {

    @org.junit.Test
    public void expression() {
        TableManager.regGlobalFunction(new TestFun());
        TExprParser parser = new TExprParser("(1+(2+var1)/var2)*mins(1,2,3,4,-1)");
        System.out.println(parser.calculate(new TExprParams().appendParam("var1", 100).appendParam("var2", 10)));
    }

}
