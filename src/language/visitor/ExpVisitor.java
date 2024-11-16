package language.visitor;

import language.operators.*;

public interface ExpVisitor {
	public void visit(PlusExp e);
	public void visit(MinusExp e);
	public void visit(DivExp e);
	public void visit(MulExp e);
	public void visit(ModExp e);
	public void visit(PowExp e);
	public void visit(NumExp e);
	
	public void visit(NotExp e);
	public void visit(AndExp e);
	public void visit(OrExp e);

	public void visit(EqExp e);
	public void visit(NeqExp e);
	public void visit(GtExp e);
	public void visit(GteExp e);
	public void visit(LtExp e);
	public void visit(LteExp e);
	
	public void visit(BlockExp e);
	
	public void visit(IfExp e);
	public void visit(WhileExp e);
	
	public void visit(GenExp e);
	public void visit(DeclExp e);
	public void visit(AssignExp e);
	public void visit(LValueExp e);
	public void visit(RValueExp e);
}
