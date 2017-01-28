import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>{

	private Node<T> head;

	// constructs empty LinkedList
	public LinkedList() {
		this.head = null;
	}

	// constructs a LinkedList with an array of elements
	// for array [1,2,3,4,5] the list will be constructed as
	// 5->4->3->2->1->null
	public LinkedList(T[] array) {
		for (T data: array){
			this.addFirst(data);
		}

	}

	// isEmpty
	public boolean isEmpty(){
		return this.head ==  null;
	}

	// add at the beginning	
	public void addFirst(T data){
		Node<T> newnode = new Node<T>(data, head); 
		head = newnode;
	}

	// add at the End
	public void addLast(T data){
		if( head == null)
			addFirst(data);
		else
		{
			Node<T> tmp = head;
			while(tmp.next != null) tmp = tmp.next;

			tmp.next = new Node<T>(data, null);
		}
	}

	// getFirst
	public T getFirst(){
		if (isEmpty())
			throw new NoSuchElementException("Empty List");
		return head.data;
	}

	// getLast
	public T getLast(){
		if (isEmpty())
			throw new NoSuchElementException("Empty List");
		Node<T> ptr = head;
		while (ptr!=null && ptr.next != null){
			ptr = ptr.next;
		}
		return ptr.data;
	}

	// get (index) - 0 based
	public T get(int index){
		if (isEmpty())
			throw new NoSuchElementException("Empty List");
		Node<T> ptr = head;
		for (int i = 0; i < index; i++){
			ptr = ptr.next;
		}
		return ptr.data;

	}
	// removeFirst
	public T removeFirst(){
		if (isEmpty())
			throw new NoSuchElementException("Empty List");
		T data = head.data;
		head = head.next;
		return data;
	}

	// removeLast
	public T removeLast(){
		if (isEmpty())
			throw new NoSuchElementException("Empty List");
		Node<T> ptr = head;
		while (ptr!=null && ptr.next != null){
			ptr = ptr.next;
		}
		return ptr.data;
	}

	// remove (key)
	public T remove(T key){
		for (Node<T> ptr = head; ptr !=null; ptr = ptr.next){
			if (ptr.next.data == key){
				T ret = ptr.next.data;
				ptr.next = ptr.next.next;
				return ret;
			}
		}
		throw new NoSuchElementException();
	}

	//reverse
	public LinkedList<T> reverse(){
		LinkedList<T> twin = new LinkedList<T>();
		for (Node<T> ptr = head; ptr!=null ; ptr = ptr.next)
			twin.addFirst(ptr.data);
		return twin;
	}

	// insertBefore
	public void insertBefore(T key, T data){
		for (Node<T> ptr = head; ptr!=null ; ptr = ptr.next){
			if (ptr.next.data == key) {
				Node<T> tmp = new Node<>(data, ptr.next);
				ptr.next = tmp;
				return;
			}
		}

	}

	// insertAfter
	public void insertAfter(T key, T data){
		for (Node<T> ptr = head; ptr!=null ; ptr = ptr.next){
			if (ptr.data == key) {
				Node<T> tmp = new Node<>(data, ptr.next);
				ptr.next = tmp;
				return;
			}
		}

	}
	//copy
	public LinkedList<T> copy(){
		LinkedList<T> twin = new LinkedList<T>();
		for (Node<T> ptr = head; ptr!=null ; ptr = ptr.next)
			twin.addFirst(ptr.data); // O(n)
		//twin.addLast(data); --> O(n2)
		return twin.reverse();
	}
	//copy2
	public LinkedList<T> copy2(){ // More efficient
		LinkedList<T> twin = new LinkedList<T>();
		twin.head = new Node<>(head.data, null);
		Node<T> ptrOrg = this.head;
		Node<T> ptrTwin = twin.head;
		while (ptrOrg.next != null){
			ptrOrg = ptrOrg.next;
			Node<T> tmp = new Node<>(ptrOrg.data, null);
			ptrTwin.next = tmp;
			ptrTwin = ptrTwin.next;
		}
		return twin;
	}

	//copy
	public LinkedList<T> copy3(){
		LinkedList<T> twin = new LinkedList<T>();
		for (Node<T> ptr = head; ptr!=null ; ptr = ptr.next)
			//twin.addFirst(ptr.data); // O(n)
			twin.addLast(ptr.data); //--> O(n2)
		return twin;
	}

	// Introduce loop in the LinkedList
	public void introduceLoop(int loopIndex){
		Node<T> ptr1 = head;
		Node<T> ptr2 = head;
		for(int i = 0; i < loopIndex; i++) {
			ptr1 = ptr1.next;
		}

		for(ptr2 = head; ptr2.next !=null ; ptr2= ptr2.next);

		ptr2.next = ptr1;
	}

	// getEndofLoop
	public Node<T> getEndofLoop(){
		if (isEmpty())
			return null;
		Node<T> hare = whereHareAndTortoiseMeet();
		if (hare == null) // no loop
			return null;
		Node<T> tortoise = head;
		while (tortoise.next != hare.next){ // where hare's next will meet the tortoise is the start of loop;
			hare = hare.next;
			tortoise = tortoise.next;
		}
		return hare; 
	}

	// removeLoop
	public void removeLoop(){
		Node<T> end = getEndofLoop();
		if (end != null)
			end.next = null;
	}

	// test loop
	public boolean testLoop(){
		if (isEmpty())
			return false;
		Node<T> tortoise = head;
		Node<T> hare = head;

		while (hare != null && hare.next != null){
			tortoise = tortoise.next;
			hare  =  hare.next.next;
			if (hare == tortoise)
				return true;
		}
		return false;
	}

	// test loop
	public Node<T> whereHareAndTortoiseMeet(){
		if (isEmpty())
			return null;
		Node<T> tortoise = head;
		Node<T> hare = head;

		while (hare != null && hare.next != null){
			tortoise = tortoise.next;
			hare  =  hare.next.next;
			if (hare == tortoise)
				return hare; // return where hare and tortoise meet
		}
		return null;
	}

	// find mid (second of two in case of even)
	public T findMid(){
		if (isEmpty())
			throw new RuntimeException("Empty List");
		Node<T> tortoise = head;
		Node<T> hare = head;

		while (hare != null && hare.next != null){
			tortoise = tortoise.next;
			hare  =  hare.next.next;
			if (hare == tortoise)
				throw new RuntimeException("Loop Detected");
		}
		return tortoise.data;
	}

	private static class Node<T> {
		T data;
		Node<T> next;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// For Loop case
		// ==========================
		if (this.testLoop()) {
			Node<T> endNode = getEndofLoop();
			for(T data : this){
				sb.append(data).append("->");
				if (endNode.data.equals(data)){
					sb.append("LOOP->");
					sb.append(endNode.next.data);
					return sb.toString();
				}
			}
		}
		// ==========================
		for(T data : this){
			sb.append(data).append("->");
		}
		sb.append("NULL");
		return sb.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T>{

		private Node<T> nextNode;

		public LinkedListIterator() {
			nextNode = head;
		}

		@Override
		public boolean hasNext() {
			if (nextNode != null)
				return true; // there is a next element
			else
				return false; // no next element
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Node<T> tmp = nextNode;
			nextNode = nextNode.next;
			return tmp.data;
		}

	}

	/* unit tests */
	public static void main(String[] args) {

		// create a linked list using addFirst
		// 1->2->3->4->5->null
		LinkedList<String> list1 = new LinkedList<String>();
		list1.addFirst("5");
		list1.addFirst("4");
		list1.addFirst("3");
		list1.addFirst("2");
		list1.addFirst("1");

		System.out.println(list1.toString());

		// create a linked list using addLast
		// 1->2->3->4->5->null
		LinkedList<String> list2 = new LinkedList<String>();
		list2.addLast("1");
		list2.addLast("2");
		list2.addLast("3");
		list2.addLast("4");
		list2.addLast("5");

		System.out.println(list2.toString());

		//reverse the list
		System.out.println(list1.reverse());

		//reverse the list
		System.out.println(list2.reverse());

		//copy the list
		System.out.println(list1.copy());
		System.out.println(list2.copy());

		//copy the list
		System.out.println("Copy 2");
		System.out.println(list1.copy2());
		System.out.println(list2.copy2());

		//create a linked list
		Integer[] array = (Integer[]) Arrays.asList(1,2,3,4,5).toArray();
		LinkedList<Integer> list3 = new LinkedList<Integer>(array);
		System.out.println(list3);

		// insert before
		// 1->2->_->4->5->null
		LinkedList<String> list4 = new LinkedList<String>();
		list4.addLast("1");
		list4.addLast("2");
		list4.addLast("4");
		list4.addLast("5");
		list4.insertBefore("4", "3");
		System.out.println(list2);

		// insert After
		// 1->2->3->_->5->null
		LinkedList<String> list5 = new LinkedList<String>();
		list5.addLast("1");
		list5.addLast("2");
		list5.addLast("3");
		list5.addLast("5");
		list5.insertAfter("3", "4");
		System.out.println(list5);

		//get
		System.out.println(list1.get(0));
		System.out.println(list1.get(1));

	}

}
