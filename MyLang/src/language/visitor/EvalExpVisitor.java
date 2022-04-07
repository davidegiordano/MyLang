package language.visitor;

import language.environment.*;
import language.operators.*;

public class EvalExpVisitor implements ExpVisitor {
	int value;
	Environment env;
	String id;
	
	public EvalExpVisitor(int value, Environment env) {
		this.value = value;
		this.env = env;
	}

	public EvalExpVisitor() {
		this(0, new Environment());
	}

	private EvalExpVisitor(EvalExpVisitor v) throws CloneNotSupportedException {
		this(0, v.env.clone());
	}
	public EvalExpVisitor(String string) {
		this(0, new Environment(string));
	}

	public EvalExpVisitor clone() throws CloneNotSupportedException{
		return new EvalExpVisitor(this);
	}
	
	public Environment getEnvironment() { return env; }
	public Integer getResult() { return value; }
	public String getId() { return id; }
	
	public void visit(NumExp e) { value = e.getValue(); }
	public void visit(PlusExp e) {
		e.left().accept(this); int arg1 = getResult();
		e.right().accept(this); int arg2 = getResult();
		value = arg1 + arg2;
	}
	public void visit(MinusExp e) {
		e.left().accept(this); int arg1 = getResult();
		e.right().accept(this); int arg2 = getResult();
		value = arg1 - arg2;
	}
	public void visit(DivExp e) {
		e.left().accept(this); int arg1 = getResult();
		e.right().accept(this); int arg2 = getResult();
		value = arg1 / arg2;
	}
	public void visit(MulExp e) {
		e.left().accept(this); int arg1 = getResult();
		e.right().accept(this); int arg2 = getResult();
		value = arg1 * arg2;
	}
	public void visit(ModExp e) {
		e.left().accept(this); int arg1 = getResult();
		e.right().accept(this); int arg2 = getResult();
		value = arg1 % arg2;
	}
	public void visit(PowExp e) {
		e.left().accept(this); int arg1 = getResult();
		e.right().accept(this); int arg2 = getResult();
		value = (int) Math.pow(arg1, arg2);
	}

	@Override
	public void visit(NotExp e) {
		e.operand().accept(this);
		int arg = getResult();
		value = (arg != 0 ? 0 : 1); //True o False
	}

	@Override
	public void visit(AndExp e) {
		e.left().accept(this); int arg1 = getResult();
		e.right().accept(this); int arg2 = getResult();
		value = Math.abs(arg1) * Math.abs(arg2); //arg1 && arg2, ma value è int
	}

	@Override
	public void visit(OrExp e) {
		e.left().accept(this); int arg1 = getResult();
		e.right().accept(this); int arg2 = getResult();
		value = Math.abs(arg1) + Math.abs(arg2); //arg1 || arg2, ma value è int
	}

	@Override
	public void visit(EqExp e) {
		e.left().accept(this); int l = getResult();
		e.right().accept(this); int r = getResult();
		value = Integer.compare(l, r) == 0 ? l == 0? 1 : l : 0;
	}

	@Override
	public void visit(NeqExp e) {
		e.left().accept(this); int l = getResult();
		e.right().accept(this); int r = getResult();
		value = Integer.compare(l, r) == 0 ? 0 : l == 0? 1 : l;
	}

	@Override
	public void visit(GtExp e) {
		e.left().accept(this); int l = getResult();
		e.right().accept(this); int r = getResult();
		value = (l > r ? l == 0? 1 : l : 0);
	}

	@Override
	public void visit(GteExp e) {
		e.left().accept(this); int l = getResult();
		e.right().accept(this); int r = getResult();
		value = (l >= r ? l == 0? 1 : l : 0);
	}

	@Override
	public void visit(LtExp e) {
		e.left().accept(this); int l = getResult();
		e.right().accept(this); int r = getResult();
		value = (l < r ? l == 0? 1 : l : 0);
	}

	@Override
	public void visit(LteExp e) {
		e.left().accept(this); int l = getResult();
		e.right().accept(this); int r = getResult();
		value = (l <= r ? l == 0? 1 : l : 0);
	}
	
	@Override
	public void visit(BlockExp e) {
		
	}

	@Override
	public void visit(IfExp e) {
		e.cond().accept(this);
		int d = ((Integer) getResult()).intValue();
		
		if(d != 0) { //se la condizione è vera
			e.action().accept(this);
		}
		else if(e.elseStat() != null) {
			e.elseStat().accept(this); //esegue else se presente
		}
	}
	
	@Override
	public void visit(WhileExp e) {
		e.cond().accept(this);
		int d = ((Integer) getResult()).intValue();
		
		while(d != 0) { //finchè la condizione è vera
			e.action().accept(this); //esegue
			e.cond().accept(this); //riverifica
			d = ((Integer) getResult()).intValue();
		}
	}
	
	@Override
	public void visit(GenExp e) {
		//e.accept(this);
		id = e.getName();
	}
	
	@Override
	public void visit(DeclExp e) {
		e.left().accept(this); String lres = getId();//String lres = getResult().toString();
		e.right().accept(this); Integer rres = getResult();
		
		if(this.env.has(lres)) {
			throw new IllegalArgumentException("La variabile "
					+ lres + " è già presente");
		}
		this.env.add(lres, rres);
	}
	
	@Override
	public void visit(AssignExp e) {
		e.left().accept(this); String id = getId();
		e.right().accept(this); Integer val = getResult();
		
		/*if(this.env.has(id)) {
			e.right().accept(this);
			
			value = this.env.setVariable(id, val).getValue();
		}*/
		this.env.add(id, val); // Entry sostituita se esiste
	}

	@Override
	public void visit(LValueExp e) {
		id = e.getName();
		
		if (env.has(id)) { //Stampa valore per comodità
			System.out.println(env.get(id).getValue());
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void visit(RValueExp e) {
		String id = e.getName();
		Integer val = env.get(id).getValue(); // Recupero valore
		
		if (val != null) value = val; // Memorizzo valore
		else { // Non dovrebbe mai accadere
			throw new RuntimeException("Invalid identifier");
		}
	}

}
