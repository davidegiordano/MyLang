package language.operators;

import language.visitor.ExpVisitor;

public class WhileExp extends Exp {
	private Exp cond, action = null;
	
	public WhileExp(Exp cond) {
		this.cond = cond;
	}

	public Exp cond() { return cond; }
	public Exp action() { return action; }
	public void setAction(Exp a) { this.action = a; }
	
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("while(");
		buffer.append(cond.toString());
		buffer.append(")");
		if(action != null)
			buffer.append(action.toString());
		
		return buffer.toString();
	}
	
	@Override
	public void accept(ExpVisitor v) { v.visit(this); }

}
