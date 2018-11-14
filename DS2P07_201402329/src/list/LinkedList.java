package list;

public class LinkedList<T> extends List<T>{
	private LinkedNode<T> _head;
	private int _size;
	
	public LinkedList() {
		this.reset();
	}
	private LinkedNode<T> head(){
		return this._head;
	}
	private void setHead(LinkedNode<T> newHead) {
		this._head = newHead;
	}
	@Override
	public int size() {
		return this._size;
	}
	private void setSize(int newSize) {
		this._size = newSize;
		
	}
	
	@Override
	public boolean isEmpty() {
		return (this.size()==0);
	}
	@Override
	public boolean isFull() {
		return false;
	}
	@Override
	public boolean add(T anElement) {
		LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement,this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size()+1);
		return true;
	}
	@Override
	public T removeAny() {
		if(this.isEmpty()) {
			return null;
		}
		else {
			T removedElement = this.head().element();
			this.setHead(this.head().next());
			this.setSize(this.size()-1);
			return removedElement;
		}
	}
	public IteratorForLinkedList iterator() {
		return new IteratorForLinkedList();
	}
	public class IteratorForLinkedList implements Iterator<T> {
		LinkedNode<T> _nextNode;
		
		private LinkedNode<T> nextNode(){
			return this._nextNode;
		}
		private void setNextNode(LinkedNode<T> newLinkedNode) {
			this._nextNode = newLinkedNode;
		}
		private IteratorForLinkedList() {
			this.setNextNode(LinkedList.this.head());
		}
		@Override
		public boolean hasNext() {
			return (this.nextNode()!=null);
		}

		@Override
		public T next() {
			T nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}
		
	}
	@Override
	public void reset() {
		this.setSize(0);
		this.setHead(null);
	}
	@Override
	public Iterator<T> listIterator() {
		// TODO Auto-generated method stub
		return new IteratorForLinkedList();
	}
	/*private class IteratorForLinkedList implements Iterator<E>{
		LinkedNode<E> _nextNode;
		
		private LinkedNode<E> nextNode(){
			return this._nextNode;
		}
		
		@SuppressWarnings("unchecked")
		private LinkedListIterator() {
			this.setNextNode((LinkedNode<E>) LinkedList.this.head());
		}
		private void setNextNode(LinkedNode<E> newLinkedNode) {
			this._nextNode = newLinkedNode;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (this.nextNode()!=null);
		}

		@Override
		public E next() {
			Ftemnsorw
			r51// TODO Auto-generated method stub
			E nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}
		
	}*/

	
}
