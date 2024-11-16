package main;

import java.util.Vector;

import language.operators.Exp;
import language.visitor.EvalExpVisitor;

public class SolveInfo {
	private Parser p;
	private EvalExpVisitor v;
	
	public SolveInfo(Parser p, EvalExpVisitor v) {
		this.p = p;
		this.v = v;
	}
	
	public void solve() {
		p.run();
		
		Vector<Exp> bufferExp = p.getBufferExp();
		
		if(!bufferExp.isEmpty()) {
			for (Exp exp : bufferExp) {
				exp.accept(v);
				System.out.println(v.getResult().toString());
			}
		}
		
		return;
	}
}
