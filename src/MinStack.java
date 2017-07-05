package com.nav;

// Stack to get min in O(1)

import java.util.Stack;

public class MinStack {

	private Stack<Integer> stack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();

	public void push(Integer data){
		Integer min = data;
		if (minStack.empty() == false){
			if (data > minStack.peek() )
				min = minStack.peek();
		}
		stack.push(data);
		minStack.push(data);

	}

	public Integer pop(){
		minStack.pop();
		return stack.pop();
	}

	public Integer peek(){
		return stack.peek();
	}
	
	public Integer getMin(){
		return minStack.peek();
	}

}
