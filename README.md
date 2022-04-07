# MyLang

Un piccolo progetto per comprendere meglio il corso Linguaggi e Modelli Computazionali M e la creazione di un linguaggio

Alcune funzionalità:
- Solo variabili di tipo int
- In pratica tutto è un'espressione (o almeno credo)
- Espressioni matematiche e logiche più comuni presenti
- If e while implementati
- L-Value e R-Value diversificati nell'assegnamento (uso di $ per indicare R-Value)
- Uso di ; come separatore di espressioni
- Uso di parola chiave EOP (End Of Program) per terminare il programma

Grammatica:

- DECL ::= int ID = EXP;

- STAT ::= ; | EXP; | BLOCK | IFSTAT | WHILESTAT
- BLOCKSTAT ::= { {DECL}{STAT} }	
- IFSTAT ::= if( EXP ) STAT [else STAT]
- WHILESTAT ::= while( EXP ) STAT

- EXP ::= TERM
- EXP ::= EXP [+|-|&&||||<|<=|>|<=|==|!=] TERM
- EXP ::= ASSIGN

- ASSIGN ::= IDENT = EXP

- TERM ::= POT
- TERM ::= FACTOR * POT
- TERM ::= FACTOR / POT
- TERM ::= FACTOR % POT
- POT ::= FACTOR
- POT ::= FACTOR ^ POT
- FACTOR ::= id
- FACTOR ::= num
- FACTOR ::= $IDENT
- FACTOR ::= ! EXP
- FACTOR ::= ( EXP )

Esempio di sintassi:
int a = 10 ;
while ( a > 5 ) a = $ a - 1 ;
EOP
