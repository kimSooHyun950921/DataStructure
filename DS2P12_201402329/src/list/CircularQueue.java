package list;

public class CircularQueue<T> implements Queue<T>{
	private final static int DEFAULT_CIRCULAR_QUEUE_CAPACITY = 1000;
	
	private int _size;
	private int _capacity;
	private T[] _element;
	private int _rear;
	private int _front;
	
	private int capacity() {
		return this._capacity;
	}
	private void setCapacity(int aCapacity) {
		this._capacity = aCapacity;
	}
	
	private T[] elements() {
		return this._element;
	}
	private void setElement(T[] aElements) {
		this._element = aElements;
	}
	
	private int rear() {
		return this._rear;
	}
	private void setRear(int aRear) {
		this._rear = aRear;
	}
	
	private int front() {
		return this._front;
	}
	private void setFront(int aFront) {
		this._front = aFront;
	}
	
	public CircularQueue() {
		this(DEFAULT_CIRCULAR_QUEUE_CAPACITY);
	}
	@SuppressWarnings("unchecked")
	public CircularQueue(int givenCapacity) {
		this.setCapacity(givenCapacity +1);
		this.setElement((T[]) new Object[this.capacity()]);
		
		this.setFront(0);
		this.setRear(-1);
	}
	@Override
	public int size() {
		return this._size;
	}
	public void setSize(int aSize) {
		this._size = aSize;
	}
	@Override
	public void reset() {
		
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean add(T element) {
		if(this.isFull()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if(this.rear() == (this.capacity()-1)) {
			this.setRear(0);
		}
		else {
			this.setRear(this.rear()+1);
		}
		this.elements()[this.rear()] = element;
		this.setSize(this.size()+1);
		return true;
	}
	@Override
	public boolean offer(T element) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public T remove() {
		if(this.isEmpty()) {
			return null;
		}
		T item = this.peek();
		this.setFront(this.front()+1);
		if(this.front() == this.capacity()) {
			this.setFront(0);
		}
		this.setSize(this.size()-1);
		return item;
	}
	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T element() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T peek() {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return this.elements()[this.front()];
	}

}
