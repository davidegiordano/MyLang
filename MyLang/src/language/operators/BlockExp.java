package language.operators;

import language.visitor.ExpVisitor;
import java.util.Vector;

public class BlockExp extends Exp {
	private Vector<Exp> statements;
	
	public BlockExp() {
		this.statements = new Vector<Exp>();
	}
	
	@Override
	public String toString() {
		return "BlockExp [statements=" + statements + "]";
	}
	
	public void add(Exp exp) {
		this.statements.add(exp);
	}
	
	public Exp get(int index) {
		return statements.elementAt(index);
	}

	@Override
	public void accept(ExpVisitor v) { v.visit(this); }

}
