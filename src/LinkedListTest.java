import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


public class LinkedListTest {
	
	private static LinkedList<Integer> emptyList;
	private static LinkedList<Integer> ListWithOneElement;
	private static LinkedList<Integer> ListWithtwoElement;
	private static LinkedList<Integer> ListWiththreeElement;
	private static LinkedList<Integer> ListWithtenElement;
	private static LinkedList<Integer> ListWithTenThousandElement;
	
	@BeforeClass
	public static void init(){
		emptyList = new LinkedList<Integer>();
		Integer[] oneElementArray = new Integer[] {1};
		Integer[] twoElementArray = new Integer[] {1,2};
		Integer[] threeElementArray = new Integer[] {1,2,3};
		Integer[] tenElementArray = new Integer[] {1,2,3,4,5,6,7,8,9};
		Integer[] thousandElementArray = new Integer[10000000];
		for (int i = 0; i < thousandElementArray.length; i++){
			thousandElementArray[i] = i;
		}
		ListWithOneElement = new LinkedList<Integer>(oneElementArray);
		ListWithtwoElement = new LinkedList<Integer>(twoElementArray);
		ListWiththreeElement = new LinkedList<Integer>(threeElementArray);
		ListWithtenElement = new LinkedList<Integer>(tenElementArray);
		ListWithTenThousandElement = new LinkedList<Integer>(thousandElementArray);
		
	}
	
	
	@Test
	public final void testIsEmpty() {
		assertTrue(emptyList.isEmpty());
		assertFalse(ListWithtenElement.isEmpty());
	}
	
	@Test
	public final void testAddFirst() {
		
		LinkedList<Integer> twinEmptyList = emptyList.copy();
		twinEmptyList.addFirst(9);
		assertEquals("9->NULL", twinEmptyList.toString());
		
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		twinThreeList.addFirst(9);
		assertEquals("9->3->2->1->NULL", twinThreeList.toString());

	}
	
	@Test
	public final void testAddLast() {
		LinkedList<Integer> twinEmptyList = emptyList.copy();
		twinEmptyList.addLast(9);
		assertEquals("9->NULL", twinEmptyList.toString());
		
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		twinThreeList.addLast(9);
		assertEquals("3->2->1->9->NULL", twinThreeList.toString());
		
	}
	
	@Test
	public final void testReverse() {
		LinkedList<Integer> twinEmptyList = emptyList.copy();
		twinEmptyList.reverse();
		assertEquals("NULL", twinEmptyList.toString());
	
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		assertEquals("1->2->3->NULL", twinThreeList.reverse().toString());
		
	}
	
	@Test(expected=NoSuchElementException.class)
	public final void testRemoveFirstEmpty() {
		LinkedList<Integer> twinEmptyList = emptyList.copy();
		twinEmptyList.removeFirst();
	}
	
	@Test(expected=NoSuchElementException.class)
	public final void testRemoveLastEmpty() {
		LinkedList<Integer> twinEmptyList = emptyList.copy();
		twinEmptyList.removeLast();
	}
	
	@Test
	public final void testCopy() {
		Long start = System.currentTimeMillis();
		LinkedList<Integer> twinList = ListWithTenThousandElement.copy();
		Long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	@Test
	public final void testCopy2() {
		Long start = System.currentTimeMillis();
		LinkedList<Integer> twinList = ListWithTenThousandElement.copy2();
		Long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	@Test
	public final void testGet() {
		assertEquals(5, ListWithtenElement.reverse().get(4).intValue());
	}
	
	@Test
	public final void testLoop() {
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		twinThreeList.introduceLoop(1);
		assertTrue(twinThreeList.testLoop());
		twinThreeList.removeLoop();
		twinThreeList.addFirst(7);
		twinThreeList.addFirst(8);
		twinThreeList.addFirst(9);
		assertEquals("9->8->7->3->2->1->NULL", twinThreeList.toString());
		twinThreeList.introduceLoop(3);
		assertEquals("9->8->7->3->2->1->LOOP->3", twinThreeList.toString());
	}
	
	@Test
	public final void testLoop1() {
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		twinThreeList.toString();
		assertFalse(twinThreeList.testLoop());
	}
	
	@Test
	public final void testLoop3() {
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		twinThreeList.introduceLoop(1);
		assertEquals("3->2->1->LOOP->2",twinThreeList.toString());
	}
	
	@Test
	public final void testLoop4() {
		LinkedList<Integer> twinEmptyList = emptyList.copy();
		assertFalse(twinEmptyList.testLoop());
	}
	
	@Test
	public final void testRemoveLoop() {
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		twinThreeList.introduceLoop(1);
		assertEquals("3->2->1->LOOP->2",twinThreeList.toString());
		twinThreeList.removeLoop();
		assertEquals("3->2->1->NULL",twinThreeList.toString());
	}
	
	@Test
	public final void testMid() {
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		assertEquals(2,twinThreeList.findMid().intValue());
	}
	
	@Test
	public final void testMid1() {
		LinkedList<Integer> twinThreeList = ListWiththreeElement.copy();
		twinThreeList.addFirst(9);
		assertEquals(2,twinThreeList.findMid().intValue());
	}
	
	@Test(expected=RuntimeException.class)
	public final void testMid3() {
		LinkedList<Integer> twinEmptyList = emptyList.copy();
		twinEmptyList.findMid();
	}
	
	/*	@Test
	public final void testCopy3() {
		Long start = System.currentTimeMillis();
		LinkedList<Integer> twinList = ListWithTenThousandElement.copy3();
		Long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	@Test
	public final void testRemoveLast() {
		assertTrue(emptyList.isEmpty());
		assertFalse(ListWithtenElement.isEmpty());
	}
	
	@Test
	public final void testGet() {
		assertTrue(emptyList.isEmpty());
		assertFalse(ListWithtenElement.isEmpty());
	}
	
	@Test
	public final void testCopy() {
		assertTrue(emptyList.isEmpty());
		assertFalse(ListWithtenElement.isEmpty());
	}
	
	@Test
	public final void testCopy2() {
		assertTrue(emptyList.isEmpty());
		assertFalse(ListWithtenElement.isEmpty());
	}*/

}
