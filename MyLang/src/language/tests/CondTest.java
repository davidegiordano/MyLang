package language.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import language.operators.*;
import language.visitor.EvalExpVisitor;

public class CondTest {
	
	@Test
	public void testEq() {
		Exp e = new EqExp(new NumExp(2), new NumExp(2)); //2 == 2
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);
		
		assertTrue("Expected True", v.getResult() == 2);
	}
	
	@Test
	public void testNeq() {
		Exp e = new NeqExp(new NumExp(2), new NumExp(3)); //2 != 2
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);
		
		assertTrue("Expected True", v.getResult() == 2);
	}
	
	@Test
	public void testGt() {
		Exp e = new GtExp(new NumExp(2), new NumExp(1)); //2 > 1
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);
		
		assertTrue("Expected True", v.getResult() == 2);
	}
	
	@Test
	public void testGte() {
		Exp e = new GteExp(new NumExp(2), new NumExp(2)); //2 >= 2
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);
		
		assertTrue("Expected True", v.getResult() == 2);
	}
	
	@Test
	public void testLt() {
		Exp e = new LtExp(new NumExp(2), new NumExp(3)); //2 < 3
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);
		
		assertTrue("Expected True", v.getResult() == 2);
	}
	
	@Test
	public void testLte() {
		Exp e = new EqExp(new NumExp(2), new NumExp(2)); //2 <= 2
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);
		
		assertTrue("Expected True", v.getResult() == 2);
	}
	
	@Test
	public void testNot() {
		Exp e = new NotExp(new EqExp(new NumExp(2), new NumExp(2))); //!(2 == 2)
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);

		assertTrue("Expected False", v.getResult() == 0);
	}
	
	@Test
	public void testAnd() {
		Exp l = new EqExp(new NumExp(2), new NumExp(2)); //(2 == 2)
		Exp r = new GteExp(new NumExp(3), new NumExp(2)); //(3 >= 2)
		Exp e = new AndExp(l, r);
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);

		assertTrue("Expected True", v.getResult() == 6); //2 * 3
	}
	
	@Test
	public void testOr() {
		Exp l = new EqExp(new NumExp(2), new NumExp(2)); //(2 == 2)
		Exp r = new GteExp(new NumExp(3), new NumExp(2)); //(3 >= 2)
		Exp e = new OrExp(l, r);
		
		EvalExpVisitor v = new EvalExpVisitor();
		e.accept(v);

		assertTrue("Expected True", v.getResult() == 5); //2 + 3
	}
}
