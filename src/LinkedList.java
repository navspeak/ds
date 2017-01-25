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
		return head.data;
	}

	// getLast
	public T getLast(){
		Node<T> ptr = head;
		while (ptr!=null && ptr.next != null){
			ptr = ptr.next;
		}
		return ptr.data;
	}

	// removeFirst
	public T removeFirst(){
		T data = head.data;
		head = head.next;
		return data;
	}

	// removeLast
	public T removeLast(){
		Node<T> ptr = head;
		while (ptr!=null && ptr.next != null){
			ptr = ptr.next;
		}
		return ptr.data;
	}

	//reverse
	public LinkedList<T> reverse(){
		LinkedList<T> twin = new LinkedList<T>();
		for (Node<T> ptr = head; ptr!=null ; ptr = ptr.next)
			twin.addFirst(ptr.data);
		return twin;
	}

	//copy
	public LinkedList<T> copy(){
		LinkedList<T> twin = new LinkedList<T>();
		for (Node<T> ptr = head; ptr!=null ; ptr = ptr.next)
			twin.addFirst(ptr.data); // O(n)
		//twin.addLast(data); --> O(n2)
		return twin.reverse();
	}


	private static class Node<T> {
		T data;
		Node next;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
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
		
		//create a linked list
		Integer[] array = (Integer[]) Arrays.asList(1,2,3,4,5).toArray();
		LinkedList<Integer> list3 = new LinkedList<Integer>(array);
		System.out.println(list3);
		


	}

}
