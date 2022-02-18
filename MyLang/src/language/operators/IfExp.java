package language.operators;

import language.visitor.ExpVisitor;

public class IfExp extends Exp {
	private Exp cond, action = null, elseStat = null;
	
	public IfExp(Exp c) { cond = c; }
	
	public Exp cond() { return cond; }
	public Exp action() { return action; }
	public Exp elseStat() { return elseStat; }
	public void setAction(Exp a) { this.action = a; }
	public void setElseStatement(Exp s) { this.elseStat = s; }
	
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		
		buffer.append("if(");
		buffer.append(cond.toString());
		buffer.append(")");
		if(action != null)
			buffer.append(action.toString());
		if(elseStat != null)
			buffer.append(elseStat.toString());
		
		return buffer.toString();
	}
	
	@Override
	public void accept(ExpVisitor v) { v.visit(this); }

}
