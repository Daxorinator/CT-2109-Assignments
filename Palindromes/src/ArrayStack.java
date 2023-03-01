/*

	Array-based implement of the Stack ADT
	Taken from Blackboard, modified 2023/03/28
	to remove JOptionPane to allow compatibility
	with command-line only applications.

 */

public class ArrayStack implements Stack {
	protected int capacity; // The actual capacity of the stack array
	protected static final int CAPACITY = 1000;	// default array capacity
	protected Object[] stack_array; // array used to implement the stack
	protected int top = -1; // index for the top of the stack

	public ArrayStack() {
		// default constructor: creates stack with default capacity
		this(CAPACITY);
	}

	public ArrayStack(int cap) {
		// this constructor allows you to specify capacity of stack
		capacity = (cap > 0) ? cap : CAPACITY;
		stack_array = new Object[capacity];
	}

	public void push(Object element) {
		if (isFull()) {
			System.err.println("ERROR: Stack is full.");
			return;
		}
		top++;
		stack_array[top] = element;
	}

	public Object pop() {
		Object element;
		if (isEmpty()) {
			System.err.println("ERROR: Stack is empty.");
			return null;
		}
		element = stack_array[top];
		stack_array[top] = null;
		top--;
		return element;
	}

	public Object top() {
		if (isEmpty()) {
			System.err.println("ERROR: Stack is empty.");
			return null;
		}
		return stack_array[top];
	}

	public boolean isEmpty() {
		return (top < 0);
	}

	public boolean isFull() {
		return (top == capacity-1);
	}

	public int size() {
		return (top + 1);
	}
}