/**
 * Set interface. 
 * @elements objects of type E
 * @structure none
 * @domain  no duplicate values
 */
public interface Set<E extends Comparable <E>> {
	
	
	/*
	 * @pre -
	 * @post - the new set is an empty set
	 * Set();
	 */
	
	/*
	 * @pre - 
	 * @post - the new set contains the list
	 * Set(List e);
	 * 
	 */
	
	
	/*
	 * @pre -
	 * @post - the set contains the element
	 * 
	 */
	
	void add(E e);
	/*
	 * @pre -
	 * @post - True is returned if element is in the set
	 * 		- False is returned if the element is not in the set
	 * 
	 * 
	 */
	
	boolean isInTheSet(E e);
	
	/*
	 * @pre - the element is in the set
	 * @post - the element has been removed from the set
	 * 
	 * 
	 */
	
	void remove (E e);
	
	/*
	 * @pre - the set is not empty
	 * @post - An element from the set has been returned
	 * 
	 * 
	 */
	
	E get ();
	
	/*
	 * @pre 
	 * @post returns a set with elements that are either in the first set/second set or both
	 */
	
	Set<E> uninon(Set<E> other);
	
	/*
	 * @pre
	 * @post - returns a set with elements that are in both the first and second set
	 */
	
	Set<E> intersection(Set<E> other);
	
	/*
	 * @pre - 
	 * @post - returns a set with elements that are in the first set but not in the second set
	 */
	
	Set<E> complement(Set<E> other);
	
	/*
	 * @pre - 
	 * @post - returns a set with elements that are in the first and second set but not both 
	 */
	
	Set<E> symmetricDifference(Set<E> other);
	/*
	 *  @pre - 
	 *  @post - the number of elements in the set has been returned 
	 * 
	 */

	 
	int size();
	
	/* 
	 * @pre -
	 * @post - True is return if the set is empty
	 * 		 - False is returned if the set is not empty
	 */
	
	boolean isEmpty();
	
	
	/*
	 * @pre - 
	 * @post - a deep copy of the set is returned
	 */
	Set <E> copy();
	
	
	

}
