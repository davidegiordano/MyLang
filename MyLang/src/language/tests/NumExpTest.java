package language.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import language.operators.DivExp;
import language.operators.Exp;
import language.operators.MinusExp;
import language.operators.MulExp;
import language.operators.NumExp;
import language.operators.PlusExp;
import language.operators.PowExp;
import language.visitor.EvalExpVisitor;

public class NumExpTest {

	@Test
	public void testMinus() {
		Exp s1 = new MinusExp(new NumExp(3), new NumExp(5)); //3-5
		
		EvalExpVisitor v = new EvalExpVisitor();
		
		s1.accept(v);
		
		assertTrue("Expected -2", v.getResult() == -2);
	}
	
	@Test
	public void testMultipleOp() {
		Exp s2 = new MinusExp(
				new PlusExp(new NumExp(3), new NumExp(2)),
				new NumExp(5)); //3+2-5
		
		EvalExpVisitor v = new EvalExpVisitor();
		
		s2.accept(v);
		
		assertTrue("Expected 0", v.getResult() == 0);
	}

	@Test
	public void testMul() {
		Exp s3 = new MulExp(new PlusExp(new NumExp(3), new NumExp(3)),
				new NumExp(4)); //3+3*4
		
		EvalExpVisitor v = new EvalExpVisitor();
		
		s3.accept(v);

		assertTrue("Expected 24", v.getResult() == 24);
	}
	
	@Test
	public void testPow() {
		Exp s4 = new PowExp(new NumExp(10), new NumExp(2)); //10^2
		
		EvalExpVisitor v = new EvalExpVisitor();
		
		s4.accept(v);
		
		assertTrue("Expected 100", v.getResult() == 100);
	}
	
	@Test
	public void testDivAndPow() {
		Exp s4 = new DivExp(new PowExp(new NumExp(10), new NumExp(2)),
				new NumExp(2)); //10^2/2
		
		EvalExpVisitor v = new EvalExpVisitor();
		
		s4.accept(v);
		
		assertTrue("Expected 50", v.getResult() == 50);
	}
}
