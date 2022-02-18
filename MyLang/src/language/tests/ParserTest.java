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
		assertTrue(parser.getBufferExp().get(0) instanceof DeclExp);
		
		DeclExp decl = (DeclExp) parser.getBufferExp().get(0);
		System.out.println(decl.toString());

		assertTrue(((GenExp) decl.left()).getName().equals("a"));
		assertTrue(((NumExp) decl.right()).getValue() == 10);
		//assertTrue(a.getName().equals("a"));
		//assertTrue(a.getValue() == 10);	
	}
	
	@Test
	public void testIf() {
		String txt = "if ( 1 > 10 ) ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		
		parser.run();
		
		assertTrue(!parser.getBufferExp().isEmpty());
		System.out.println(parser.getBufferExp().toString());
	}
	
	@Test
	public void testWhile() {
		String txt = "while ( 1 > 10 ) ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		
		parser.run();
		
		assertTrue(!parser.getBufferExp().isEmpty());
		System.out.println(parser.getBufferExp().toString());
	}

}
