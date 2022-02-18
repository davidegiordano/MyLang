package language.environment;

public class Variable implements Cloneable{

	private String name;
	private int value;
	
	public Variable(String name, int value) {
		this.name = name;
		this.value = value;
	}
	public Variable(Variable v) {
		this(v.getName(), v.getValue());
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;		
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Variable [name=" + name + ", value=" + value + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj == null){
			return false;
		}
		if(!(obj instanceof Variable)){
			return false;
		}
		Variable other = (Variable) obj;
		if(this.name == null){
			if(other.name != null){
				return false;
			}
		}else if(!this.name.equals(other.name)){
			return false;
		} else if(this.value != other.value) {
			return false;
		}
		return true;
	}
	
	@Override
	public Variable clone() {
		return new Variable(this);
	}
}
