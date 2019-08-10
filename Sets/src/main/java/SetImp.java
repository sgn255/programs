public class SetImp<E extends Comparable <E>> implements Set<E> {

	private ListInterface<E> list;
	SetImp(){
		list = new List<E>();
	}
	SetImp(ListInterface<E> other){
		this.list=other;
	}
	@Override
	public void add(E e) {
		if (!isInTheSet(e)){
			list.insert(e);
		}
	}
	@Override
	public boolean isInTheSet(E e) {
		return list.find(e);
	}
	@Override
	public void remove(E e) {
		if(list.find(e)){
			list.remove();
		}
	}
	@Override
	public E get() {
		return list.retrieve();
	}
	@Override
	public Set<E> uninon(Set<E> other) {
		Set<E> lhs = this.copy();
		Set<E> rhs = other.copy();
		while (!rhs.isEmpty()){
			E rhsVal=rhs.get();
			if (!lhs.isInTheSet(rhsVal)){
				lhs.add(rhsVal);
			}
			rhs.remove(rhsVal);
		}
		return lhs;
	}
	@Override
	public Set<E> intersection(Set<E> other) {
		Set<E> lhs = this.copy();
		Set<E> rhs = other.copy();
		Set<E> result = new SetImp<E>();
		while (!rhs.isEmpty()){
			E rhsValue=rhs.get();
			if (lhs.isInTheSet(rhsValue)){
				result.add(rhsValue);
			}
			rhs.remove(rhsValue);
		}
		return result;
	}
	@Override
	public Set<E> complement(Set<E> other) {
		Set<E> lhs = this.copy();
		Set<E> rhs = other.copy();
		Set<E> result = new SetImp<E>();
		while (!lhs.isEmpty()){
			E lhsValue = lhs.get();
			if (!rhs.isInTheSet(lhsValue)){
				result.add(lhsValue);
			}
			lhs.remove(lhsValue);
		}
		return result;
	}
	@Override
	public Set<E> symmetricDifference(Set<E> other) {
		Set<E> lhs = this.copy();
		Set<E> rhs = other.copy();
		Set<E> result = new SetImp<E>();
		while(!lhs.isEmpty()){
			E lhsValue = lhs.get();
			if (!rhs.isInTheSet(lhsValue)){
				result.add(lhsValue);
				lhs.remove(lhsValue);
			}else 
				lhs.remove(lhsValue);
				rhs.remove(lhsValue);
		}
		while(!rhs.isEmpty()){
			E rhsValue = rhs.get();
			result.add(rhsValue);
			rhs.remove(rhsValue);
		}
		return result;
	}
	@Override
	public int size() {
		return list.size();
	}
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	@Override
	public Set<E> copy() {
		ListInterface<E> listResult= this.list.copy();
		Set<E> setResult = new SetImp<E>(listResult);
		return setResult;
	}
}
