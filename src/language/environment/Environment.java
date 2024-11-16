package language.environment;

import java.text.Collator;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Environment implements Cloneable{
	private Map<String, Variable> variables;
	private String name;
	
	//Coppia chiave-valore
	public Environment(String name) {
		this(name, new HashMap<String, Variable>());
	}
	public Environment() {
		this(new HashMap<String, Variable>());
	}
	public Environment(Map<String, Variable> d) {
		this("Main", d);
	}
	public Environment(String name, Map<String, Variable> d) {
		this.variables = new HashMap<String, Variable>();
		d.forEach((k, v) -> {
			this.variables.put(k, v.clone());
		});
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Variable add(String key, Integer value){
		Variable v = null;
		this.variables.put(key, v = new Variable(key,value));
		return v;
	}
	
	
	public Variable get(String key){
		return this.variables.get(key);
	}
	public boolean has(String key){
		return this.variables.containsKey(key);
	}
	
	public Variable setVariable(String key, Variable v) {
		if(this.has(key)) {
			this.variables.replace(key, this.get(key), v); //Vecchio valore, nuovo
			return this.get(key);
		}
		return null;
	}
	public Variable setVariable(String key, int newValue) {
		if(this.has(key)) {
			this.variables.replace(key, this.get(key), new Variable(key, newValue)); //Vecchio valore, nuovo
			return this.get(key);
		}
		return null;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		List<String> envKey = new LinkedList<String>(this.variables.keySet());
		Collections.sort(envKey, Collator.getInstance());
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("Environment (%-10s) is \n", this.name));
		builder.append(String.format("%-2s | %s | %s\n","K", "Name", "Value"));
		builder.append(String.format("%-2s | %s | %s\n","--", "----", "--------------------------------------->"));
		envKey.stream().map(k -> {
			Variable v = this.variables.get(k);
			return String.format("%s | %s | %d\n", k, k, v.getValue());
			}).forEach(i -> builder.append(i));
		
		return builder.toString();
	}
	
	@Override
	public Environment clone() throws CloneNotSupportedException {
		return new Environment(this.variables);
	}

	
}
