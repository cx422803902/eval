package org.goiot.utils.eval;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.goiot.utils.eval.functor.FuncFactoryAdd;
import org.goiot.utils.eval.functor.FuncFactoryMultiple;
import org.goiot.utils.eval.functor.FuncTest1;

public class TestEnterPoint {

	public static void main(String[] args) {
		String expr;

		expr = "if(not(and(1,or(0,1,0),0)),(变量x/变量y1*test($GB_VAR,test((z+乘)+y*(z+x),y,(z+x)%y),test(z,-x,y*(z+test(x,y,z))))+300*3+test(x,y,z)),0)+乘";
		expr = "test(obj)";
		expr = "switch(0, 11, 0, 22, 1, 33, test(obj))";
		expr = "concatenate(strcmp(\"dasdassadasd\",\"dasdasdasd\"),\"asdasd\",\"asdasd\")";
		expr = "replace(\"abc\",2,1,\"12\")";
		// "if(not(and(1,or(0,1,0),0)),(x/y*test($GB_VAR,test((z+x)+y*(z+x),y,(z+x)%y),test(z,-x,y*(z+test(x,y,z))))+300*3+test(x,y,z)),0)";
		// expr = "if(not(and(1,or(0,1,0),0)),99,0)";
		// expr = "x/y*test($GB_VAR,test((z+x)+y*(z+x),y,(z+x)%y)," + "test(z,-x,y*(z+test(x,y,z))))+300*3+test(x,y,z)";
		// expr = "p1.attack - p2.defense";

		TExprParser.regGlobalVariable("GB_VAR", 31.0);

		// expr = "f1(x^y, f2(y&x, z|y,~z ), f1(f2(123,13),2))+a.b.c";
		// expr = "f1(x^y, f2(y&x, z|y,~z ), f1(f2(123,13),2))";
		// expr = "min(212.2,121.21)+max(212.21,123.21)";
		// expr = "tan(1.1)+atan(2.2)+sin(3.3)+asin(0.9)+cos(0.6)+acos(0.4)";
		// expr = "-2 +(231)-43*1+62312/231%123 +8/21+(2-2 3)";
		// expr = "~212312.32123&4353.13123123^58976.1231230|987998.1123123";
		// expr = "min(212.2,121.21)+max(212.21,123.21)+a.b.c";
		// expr = "a.b.c";
		final TExprParser parser = new TExprParser(expr);

		parser.addFunction(new FuncTest1("test"));
		parser.addFunction(new FuncFactoryMultiple("f1"));
		parser.addFunction(new FuncFactoryAdd("f2"));

		final double x = 11111111.11111111;
		final double y = 22222222.22222222;
		final double z = 33333222.33333333;
		int threadNum = 10;
		final CountDownLatch cdl = new CountDownLatch(threadNum);
		final int times = 100000;
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for (int ii = 0; ii < 1; ii++) {
					long startTime = System.nanoTime();
					Object value = null, lastValue = null;
					for (int i = 0; i < times; i++) {
						try {
							TExprParams params = new TExprParams();
							params.put("乘", x);
							params.put("变量x", x);
							params.put("变量y1", y);
							params.put("x", x);
							params.put("y", y);
							params.put("z", z);
							params.put("obj", this);
							HashMap<String, Object> aParams = new HashMap<String, Object>();
							params.put("a", aParams);
							HashMap<String, Double> bParams = new HashMap<String, Double>();
							aParams.put("b", bParams);
							bParams.put("c", 11.11);
							value = parser.calculate(params);
						} catch (ExprException e) {
							e.printStackTrace();
						}
						if (lastValue == null) {
							lastValue = value;
						}
						// if (!((Number) lastValue).equals((Number) value)) {
						// ExprException.throwException("计算出两个不相等的值!");
						// }
					}
					Logger.instance.appendFormat("时间: %s nm, 结果:%s\n", System.nanoTime() - startTime, value);
				}
				cdl.countDown();
			}
		};

		long startTime = System.nanoTime();
		ExecutorService eService = Executors.newFixedThreadPool(2);
		for (int i = 0; i < threadNum; i++) {
			eService.execute(runnable);
		}
		try {
			cdl.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long totalTime = System.nanoTime() - startTime;
		Logger.instance.appendFormat("总时间: %s nm, 平均时间: %s nm\n", totalTime, totalTime / threadNum);
		eService.shutdown();

		// startTime = System.nanoTime();
		// for (int i = 0; i < 100; i++) {
		// Thread thread = new Thread(runnable);
		// thread.start();
		// }
		// try {
		// cdl.await();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println(System.nanoTime() - startTime);
	}
}
