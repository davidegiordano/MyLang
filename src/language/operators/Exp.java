package language.operators;

import language.visitor.ExpVisitor;

public abstract class Exp {
	public abstract void accept(ExpVisitor v);
}
