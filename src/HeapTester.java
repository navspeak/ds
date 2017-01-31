import static org.junit.Assert.*;

import org.junit.Test;

public class HeapTester {

	@Test(expected=Heap.HeapFullException.class)
	public final void testAdd() throws Heap.HeapFullException {
		Heap<Integer> minHeap = new MinHeap<Integer>(Integer.class, 4);
		minHeap.add(10);
		minHeap.add(15);
		minHeap.add(20);
		minHeap.add(17);
		assertEquals("[10, 15, 20, 17]", minHeap.toString());
		minHeap.add(8);
	}
	
	@Test
	public final void testAdd_Resize_Peek_Poll() throws Heap.HeapFullException {
		Heap<Integer> minHeap = new MinHeap<Integer>(Integer.class, 4);
		minHeap.add(10);
		minHeap.add(15);
		minHeap.add(20);
		minHeap.add(17);
		minHeap.increaseSize();
		minHeap.add(8);
		minHeap.add(6);
		minHeap.add(3);
		minHeap.add(4);
		assertEquals("[3, 4, 6, 10, 15, 20, 8, 17]", minHeap.toString());
		try {
			assertEquals(3, minHeap.peek().intValue());
			assertEquals(3, minHeap.poll().intValue());
		} catch (Heap.HeapEmptyException e) {
			fail("Failed in peek - Heap was empty");;
		}
	}
	
	@Test(expected=Heap.HeapEmptyException.class)
	public final void testEmptyPoll() throws Heap.HeapEmptyException {
		Heap<Integer> minHeap = new MinHeap<Integer>(Integer.class, 4);
		minHeap.poll();
	}

	@Test
	public final void testGetMax() throws Heap.HeapFullException, Heap.HeapEmptyException {
		Heap<Integer> minHeap = new MinHeap<Integer>(Integer.class, 4);
		minHeap.add(10);
		minHeap.add(15);
		minHeap.add(20);
		minHeap.add(17);
		assertEquals(20, minHeap.getMax().intValue());
	}
	
}
