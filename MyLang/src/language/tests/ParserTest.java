package language.tests;

import static org.junit.Assert.*;

import main.*;
import language.operators.*;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testDecl() {
		String txt = "int a = 10 ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		//EvalExpVisitor v = new EvalExpVisitor();
		
		parser.run();
		
		assertTrue(!parser.getBufferExp().isEmpty());
		assertTrue("Got " + parser.getBufferExp().get(0),
				parser.getBufferExp().get(0) instanceof DeclExp);
		
		DeclExp decl = (DeclExp) parser.getBufferExp().get(0);
		System.out.println(decl.toString());

		assertTrue(((LValueExp) decl.left()).getName().equals("a"));
		assertTrue(((NumExp) decl.right()).getValue() == 10);
		//assertTrue(a.getName().equals("a"));
		//assertTrue(a.getValue() == 10);	
	}
	
	@Test
	public void testBlock() {
		String txt = "{ int a = 10 ; a ; 20 * 2 ; 30 ; } EOP";

		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		
		parser.run();

		assertTrue(!parser.getBufferExp().isEmpty());
		System.out.println(parser.getBufferExp().toString());
	}
	
	@Test
	public void testIf() {
		String txt = "if ( 1 > 10 ) ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		
		parser.run();
		
		assertTrue(!parser.getBufferExp().isEmpty());
		//System.out.println(parser.getBufferExp().toString());
		
		assertTrue(parser.getBufferExp().get(0) instanceof IfExp);
		
		IfExp ifExp = (IfExp) parser.getBufferExp().get(0);
		//System.out.println(ifExp.toString());

		assertTrue(ifExp.action() instanceof EmptyExp);
	}
	

	@Test
	public void testIfElse() {
		String txt = "if ( 1 > 10 ) 1 ; else 10 ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		
		parser.run();
		
		assertTrue(!parser.getBufferExp().isEmpty());
		System.out.println(parser.getBufferExp().toString());
		
		assertTrue(parser.getBufferExp().get(0) instanceof IfExp);
		
		IfExp ifExp = (IfExp) parser.getBufferExp().get(0);
		//System.out.println(ifExp.toString());

		assertTrue(ifExp.action() instanceof NumExp);
		assertTrue(((NumExp)ifExp.action()).getValue() == 1);
		assertTrue(((NumExp)ifExp.elseStat()).getValue() == 10);
	}
	
	@Test
	public void testWhile() {
		String txt = "while ( 1 > 10 ) ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		
		parser.run();
		
		assertTrue(!parser.getBufferExp().isEmpty());
		//System.out.println(parser.getBufferExp().toString());
		
		assertTrue(parser.getBufferExp().get(0) instanceof WhileExp);
		
		WhileExp whileExp = (WhileExp) parser.getBufferExp().get(0);
		//System.out.println(ifExp.toString());

		assertTrue(whileExp.action() instanceof EmptyExp);
	}
	
	@Test
	public void testDeclWithAssign() {
		String txt = "int a = 3 ; a = $ a - 1 ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		
		parser.run();

		System.out.println(parser.getBufferExp().toString());
		assertTrue(!parser.getBufferExp().isEmpty());
		assertTrue("Got " + parser.getBufferExp().get(0),
				parser.getBufferExp().get(0) instanceof DeclExp);
		
		DeclExp decl = (DeclExp) parser.getBufferExp().get(0);
		System.out.println(decl.toString());

		assertTrue(((LValueExp) decl.left()).getName().equals("a"));
		assertTrue(((NumExp) decl.right()).getValue() == 3);
		

		assertTrue("Got " + parser.getBufferExp().get(1),
				parser.getBufferExp().get(1) instanceof AssignExp);
		
		AssignExp assign = (AssignExp) parser.getBufferExp().get(1);
		System.out.println(assign.left() + ", " + assign.right());
		assertTrue(assign.left() instanceof LValueExp);
		
		MinusExp exp = (MinusExp) assign.right();
		System.out.println(exp.left() + ", " + exp.right());
		

		//assertTrue(((LValueExp) exp.left()).getName().equals("a"));
		assertTrue(exp.left() instanceof RValueExp);
		assertTrue(((NumExp) exp.right()).getValue() == 1);
	}
	
	@Test
	public void testWhileWithAssign() {
		String txt = "int a = 3 ; while ( a > 1 ) a = $ a - 1 ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		
		parser.run();
		
		assertTrue(!parser.getBufferExp().isEmpty());
		System.out.println(parser.getBufferExp().toString());

		assertTrue("Got " + parser.getBufferExp().get(0),
				parser.getBufferExp().get(0) instanceof DeclExp);
		assertTrue("Got " + parser.getBufferExp().get(1),
				parser.getBufferExp().get(1) instanceof WhileExp);
		
		WhileExp whileExp = (WhileExp) parser.getBufferExp().get(1);
		System.out.println(whileExp);
		
		assertTrue(whileExp.cond() instanceof GtExp);
		assertTrue(whileExp.action() instanceof AssignExp);
	}

}
