package language.operators;

import language.visitor.*;

public class NumExp extends Exp {
	int val;
	
	public NumExp(int v) { val = v; }
	
	public String toString() { return "" + val; }
	public int getValue() { return val; }
	public void accept(ExpVisitor v) { v.visit(this); }
}
