package language.operators;

import language.visitor.ExpVisitor;

public class RValueExp extends Exp {
	private String name;
	private int value;
	
	public RValueExp(String name) {
		this.name = name;
	}
	
	public String toString() { return name; }
	public String getName() { return name; }
	public int getValue() { return value; }

	@Override
	public void accept(ExpVisitor v) { v.visit(this); }

}
