/**
 * Identifier interface. 
 * @elements characters
 * @structure linear
 * @domain Identifier has to start with a letter. Elements can only be alphanumeric characters
 */
public interface Identifier  {
	
	/*
	 * @pre -
	 * @post - Fail - s is not an Identifier as is doesn't contain only alpha-numerical characters
	 * 			Pass - A new Identifier has been created with the value s
	 * 			
	 * Identifier(String s) throws APExeptions
	 */

	/*
	 * @pre-
	 * @post - returns and object as a string
	 */
	String toString();
	
	/*
	 * @pre-
	 * @post- the hash value of the id Has been returned
	 */
	int hashCode();
	/*
	 * @pre-
	 * @post - returns TRUE if two identifiers are equal
	 */
	boolean equals(Object o);

	
	

}
