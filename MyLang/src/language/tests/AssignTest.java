package language.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import language.operators.*;
import language.visitor.EvalExpVisitor;

public class AssignTest {
	
	@Test
	public void testVar() {
		Exp var = new DeclExp("int", new GenExp("var"), new NumExp(0));

		EvalExpVisitor v = new EvalExpVisitor();
		var.accept(v);
		assertTrue("Expected 0", v.getEnvironment().get("var").getValue() == 0);
	}
	
	/*@Test
	public void testAssign() {
		Variable var = new Variable("var", 0);
		Exp assign = new AssignExp(var, new NumExp(0));

		EvalExpVisitor v = new EvalExpVisitor();
		var.accept(v);
		assign.accept(v);
		
		assign = new AssignExp(var, new NumExp(1));
		assign.accept(v);
		
		assertTrue("Expected 1", v.getEnvironment().get(var.getName()).getValue() == 1);
	}*/

}
