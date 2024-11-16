package language.operators;

import language.visitor.ExpVisitor;

public class GenExp extends Exp {
	private String name;
	
	public GenExp(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() { return this.getName(); }

	@Override
	public void accept(ExpVisitor v) { v.visit(this); }

}
