package com.example.javalib.arg;

import java.util.Stack;

//leetcode-641设计循环双端队列
public class Test_641 {
    int mCapacity;
    private Stack<Integer> mHeadStack = new Stack();
    private Stack<Integer> mTailStack = new Stack();

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public Test_641(int k) {
        mCapacity = k;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        mHeadStack.push(value);
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        mTailStack.push(value);
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        if (!mHeadStack.isEmpty()) {
            mHeadStack.pop();
            return true;
        }

        moveTailStackToHeadStack();
        mHeadStack.pop();
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        if (!mTailStack.isEmpty()) {
            mTailStack.pop();
            return true;
        }

        moveHeadStackToTailStack();
        mTailStack.pop();
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }

        if (!mHeadStack.isEmpty()) {
            return mHeadStack.peek();
        }

        moveTailStackToHeadStack();
        return mHeadStack.peek();
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }

        if (!mTailStack.isEmpty()) {
            return mTailStack.peek();
        }

        moveHeadStackToTailStack();
        return mTailStack.peek();
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return mHeadStack.size() + mTailStack.size() == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return mHeadStack.size() + mTailStack.size() == mCapacity;
    }

    private void moveHeadStackToTailStack() {
        int headSize = mHeadStack.size();
        for (int i = 0; i < headSize; i++) {
            mTailStack.push(mHeadStack.pop());
        }
    }

    private void moveTailStackToHeadStack() {
        int tailSize = mTailStack.size();
        for (int i = 0; i < tailSize; i++) {
            mHeadStack.push(mTailStack.pop());
        }
    }
}