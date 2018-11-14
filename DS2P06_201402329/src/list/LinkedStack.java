package list;

public class LinkedStack<E> implements Stack<E> {
	private int _size;
	private LinkedNode<E> _top;
	
	private LinkedNode<E> top(){
		return this._top;
	}
	private void setTop(LinkedNode<E> newTop) {
		this._top = newTop;
	}
	public LinkedStack() {
		this.setSize(0);
		this.setTop(null);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this._size;
	}
	public void setSize(int newSize) {
		this._size = newSize;
	}

	@Override
	public boolean isEmpty() {
		return (this.size()==0);}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean push(E anElement) {
		LinkedNode<E> newTop = new LinkedNode<E>(anElement,this.top());
		this.setTop(newTop);
		this.setSize(this.size()+1);
		return true;
	}

	@Override
	public E pop() {
		if(this.isEmpty())
			return null;
		else {
			E removedElement = this.top().element();
			this.setTop(this.top().next());
			this.setSize(this.size()-1);
			return removedElement;
		}
	}
	public IteratorForLinkedStack iterator() {
		return new IteratorForLinkedStack();
	}
	public class IteratorForLinkedStack implements Iterator<E>{
		LinkedNode<E> _nextNode;
		private IteratorForLinkedStack() {
			this.setNextNode(LinkedStack.this.top());
		}
		private LinkedNode<E> nextNod(){
			return this._nextNode;
		}
		private void setNextNode(LinkedNode<E> aLinkedNode) {
			this._nextNode = aLinkedNode;
		}
		@Override
		public boolean hasNext() {
			return (this.nextNod()!=null);
		}
		@Override
		public E next() {
			// TODO Auto-generated method stub
			E nextElement = this.nextNod().element();
			this.setNextNode(this.nextNod().next());
			return nextElement;
			
		}
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
