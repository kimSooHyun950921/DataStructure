
public class CircularQueue<T> implements Queue<T>{
	
	private T[] _elements;
	private int _capacity;
	private int _front;
	private int _rear;
	
	@SuppressWarnings("unchecked")
	public CircularQueue(int maxNumberOfElements) {
		this.setCapacity(maxNumberOfElements+1);
		this.setEelements((T[]) new Object[this.capacity()]);
		this.reset();
	}
	private T[] elements() {
		return _elements;
	}

	private void setEelements(T[] newElements) {
		this._elements = newElements;
	}

	private int capacity() {
		return _capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	private int front() {
		return _front;
	}

	private void setFront(int newFront) {
		this._front = newFront;
	}

	private int rear() {
		return _rear;
	}

	private void setRear(int newRear) {
		this._rear = newRear;
	}

	@Override
	public void reset() {
		this.setFront(0);
		this.setRear(0);
		
	}
	
	private int nextRear() {
		return (this.rear()+1) % this.capacity();
	}
	private int nextFront() {
		return(this.front()+1) % this.capacity();
	}

	@Override
	public int size() {
		if(this.front()<=this.rear()) {
			return(this.rear() - this.front());
		}
		else {
			return(this.capacity() + this.rear() - this.front());
		}
	}

	@Override
	public boolean isEmpty() {
		return (this.front() == this.rear());
	}

	@Override
	public boolean isFull() {
		return (this.front() ==this.nextRear());
	}

	@Override
	public boolean add(T anElement) {
		if(this.isFull()) {
			return false;
		}
		else {
			this.setRear(this.nextRear());
			this.elements()[this.rear()] = anElement;
			return true;
		}
		
	}

	@Override
	public T remove() {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			return null;
		}
		else {
			this.setFront(this.nextFront());
			return this.elements()[this.front()];
		}
	}

}
