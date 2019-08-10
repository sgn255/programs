
public class IdentifierImp implements Identifier{
	private String id;
	IdentifierImp(String s) throws APException{
		if(!Character.isLetter(s.charAt(0))) {
			throw new APException(s+" is not an identifer");
		}
		id = s;
	}
	public String toString() {
		return id;
	}
	@Override
	public int hashCode(){
		return id.hashCode();
	}
	@Override 
	public boolean equals (Object o) {
		return id.equals(o.toString());		
	}
}
