package list;

public class ArrayStack<E> implements Stack<E> {

	private static final int DEFAULT_CAPACIY = 100;

	private int _capacity;
	private int _top;
	private E[] _elements;

	private int capacity() {
		return _capacity;
	}

	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}

	private int top() {
		return _top;
	}

	private void setTop(int newTop) {
		this._top = newTop;
	}

	private E[] elements() {
		return _elements;
	}

	private void setElements(E[] newElements) {
		this._elements = newElements;
	}

	public ArrayStack() {
		this(ArrayStack.DEFAULT_CAPACIY);
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Object[this.capacity()]);
		this.setTop(-1);
	}

	@Override
	public void reset() {
		this.setTop(-1);
	}

	@Override
	public int size() {
		return (this.top() + 1);
	}

	@Override
	public boolean isEmpty() {
		return (this.top() == -1);
	}

	@Override
	public boolean isFull() {
		return (this.top() +1== this.capacity());
	}

	@Override
	public boolean push(E anElement) {
		if (this.isFull()) {
			return false;
		}
		this.setTop(this.top() + 1);
		this.elements()[this.top()] = anElement;
		this.setElements(this.elements());
		
		return true;
	}

	@Override
	public E pop() {
		if(this.isEmpty()) {
			return null;
		}
		E poppedElement = this.elements()[this.top()];
		this.elements()[this.top()] = null;
			this.setElements(this.elements());
		this.setTop(this.top() -1);
		return poppedElement;
		
	}

	@Override
	public E peek() {
		if(this.isEmpty()) {
			return null;
		}
		return (this.elements()[this.top()]);
	}

}
