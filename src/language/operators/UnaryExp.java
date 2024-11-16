package language.operators;

public abstract class UnaryExp extends Exp {
	Exp operand;

	public Exp operand() { return operand; }
	protected UnaryExp(Exp o) { operand = o; }
	
	abstract String myOp(); //!
	
	public String toString() { return myOp() + operand.toString(); }
}
