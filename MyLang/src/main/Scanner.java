package main;

public class Scanner extends java.util.StringTokenizer{
	
	public Scanner(String txt) {
		super(txt);
	}
	
	public Token getNextToken() {
		try {
			return new Token(nextToken().trim());
		}
		catch (java.util.NoSuchElementException e) {
			return null;
		}
	}
}
