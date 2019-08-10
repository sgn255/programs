public class List<E extends Comparable<E>> implements ListInterface<E>{

	private int numOfElements;
	private Node current;
	List(){
		numOfElements =0;
		current =null;
	}
	private class Node {
		E data;
		Node prior,
		next;
		public Node(E d) {
			this(d, null, null);
		}
		public Node(E data, Node prior, Node next) {
			this.data = data == null ? null : data;
			this.prior = prior;
			this.next = next;
		}
	}
	@Override
	public boolean isEmpty() {
		return (size()==0);
	}
	@Override
	public ListInterface<E> init() {
		if (current==null && isEmpty()){
			return null;
		}
		while (!isEmpty()){
			remove();
		}
		return null;
	}
	@Override
	public int size() {
		return numOfElements;
	}
	@Override
	public ListInterface<E> insert(E d) {
		 if(isEmpty()){
			 current = new Node(d);
			 numOfElements++;
			 return this;
		 }
		 if (d.compareTo(current.data)<0){
			 while (d.compareTo(current.data)<0 && current.prior!=null){
				 if(d.compareTo(current.prior.data)>0){
					 Node insert = new Node(d,current.prior,current);
					 current.prior=insert;
					 current=insert;
					 current.prior.next=current;
					 numOfElements++;
					 return this;
				 }
				 current=current.prior;
			 }	
			 Node insert = new Node(d,null,current);
			 current.prior=insert;
			 current=insert;
			 numOfElements++;
			 return this;
		 }
		 if(d.compareTo(current.data)>0){
			 while (d.compareTo(current.data)>0 && current.next!=null){
				 if(d.compareTo(current.next.data)<0){
					 Node insert =new Node(d,current,current.next);
					 current.next=insert;
					 current=insert;
					 current.next.prior=current;
					 numOfElements++;
					 return this;
				 }
				 current=current.next;
			 }
			 Node insert = new Node(d,current,null);
			 current.next=insert;
			 current=insert;
			 numOfElements++;
			 return this;
		 }
		 if (current.next!=null){
			 Node insert= new Node(d,current,current.next);
			 current=insert;
			 current.next.prior=current;
			 current.prior.next=current;
			 numOfElements++;
			 return this;
		 }
		 Node insert =new Node(d,current,null);
		 current=insert;
		 current.prior.next=current;
		 numOfElements++;
		 return this;
	}
	@Override
	public E retrieve() {
		return current.data;
	}
	@Override
	public ListInterface<E> remove() {
		if (size()==1){
			current=null;
		}
		else if (current.next==null){
			current=current.prior;
			current.next=null;
		}
		else if (current.prior==null){
			current=current.next;
			current.prior=null;
		}
		else {
			current.next.prior=current.prior;
			current.prior.next=current.next;
			current=current.next;
		}
		numOfElements--;
		return this;
	}
	@Override
	public boolean find(E d) {
		if (isEmpty()){
			return false;
		}
		while(d.compareTo(current.data)!=0){
			if (d.compareTo(current.data)<0){
				if (current.prior==null ||d.compareTo(current.prior.data)>0){
					return false;
				}
				if(d.compareTo(current.data)<0){
					current=current.prior;
				}
			}
			else if (d.compareTo(current.data)>0){
				if (current.next==null ||d.compareTo(current.next.data)<0){
					return false;
				}
				if (d.compareTo(current.data)>0){
					current=current.next;
				}
			}
		}
		return true;
	}
	@Override
	public boolean goToFirst() {
		if (!isEmpty()){
			while(current.prior!=null){
				current = current.prior;
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean goToLast() {
		if (!isEmpty()){
			while(current.next!=null){
				current=current.next;
			}
			return true;
		}
		return false;
	}
	@Override
	public boolean goToNext() {
		if (!isEmpty() && current.next!=null){
			current=current.next;
			return true;
		}
		return false;
	}
	@Override
	public boolean goToPrevious() {
		if (!isEmpty() && current.prior!=null){
			current=current.prior;
			return true;
		}
		return false;
	}
	@Override
	public ListInterface<E> copy() {
		ListInterface<E> result = new List<E>();
		if (goToFirst()){
			Node tempNode = current;
			result.insert(tempNode.data);
			while(goToNext()){
				tempNode=current;
				result.insert(tempNode.data);
			}
		}
		return result;
	}
}
