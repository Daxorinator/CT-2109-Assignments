public interface Queue {
	void enqueue(Object n);
	Object dequeue();
	boolean isEmpty();
	boolean isFull();
	Object front();
}