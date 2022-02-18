# MyLang

Un piccolo progetto per comprendere meglio il corso Linguaggi e Modelli Computazionali M e la creazione di un linguaggio

Grammatica:
   *    DECL ::= int ID = EXP;
	 * 
	 * 		STAT ::= ; | EXP; | BLOCK | IFSTAT | WHILESTAT
	 * 		BLOCKSTAT ::= { {DECL}{STAT} }	
	 * 		IFSTAT ::= if( EXP ) STAT [else STAT]
	 * 		WHILESTAT ::= while( EXP ) STAT
	 * 
	 * 		EXP ::= TERM
	 * 		EXP ::= EXP [+|-|&&||||<|<=|>|<=|==|!=] TERM
	 * 
	 * 		TERM ::= POT
	 * 		TERM ::= FACTOR * POT
	 * 		TERM ::= FACTOR / POT
	 * 		TERM ::= FACTOR % POT
	 * 		POT ::= FACTOR
	 * 		POT ::= FACTOR ^ POT
	 * 		FACTOR ::= id
	 * 		FACTOR ::= num
	 * 		FACTOR ::= ! EXP
	 * 		FACTOR ::= ( EXP )
