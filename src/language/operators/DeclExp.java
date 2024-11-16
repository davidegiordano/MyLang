package language.operators;

import language.visitor.ExpVisitor;

public class DeclExp extends Exp {
	private String type;
	private Exp left;
	private Exp right;
	
	public DeclExp(String type, Exp left, Exp right) {
		this.type = type;
		this.left = left;
		this.right = right;
	}
	
	public String type() { return type; }
	public Exp left() { return left; }
	public Exp right() { return right; }
	
	@Override
	public String toString() {
		return type + " " + left.toString() + " " + right.toString();
	}

	@Override
	public void accept(ExpVisitor v) { v.visit(this); }

}
