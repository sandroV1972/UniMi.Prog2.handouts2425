/*

Copyright 2024 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.s10;

import java.util.NoSuchElementException;

/**
 * A <em>queue</em> is a mutable data structure that provides access to its elements in
 * first-in/first-out order.
 *
 * <p>A <em>bounded</em> queue has an upper bound, established when a queue is created, on the
 * number of elements that can be stored in the queue.
 */
public class BoundedIntQueue {

  /** The elements in the queue. */
  private int[] elements;

  /** The index in {@link #elements} of the first queue element (or -1 if the queue is empty). */
  private int head;

  /** The index of the first free position in {@link #elements} (if the queue is not full). */
  private int tail;

  /*-
   * AF:
   *
   *  AF(elements, head, tail) =
   *      [elements[head], elements[head+1], ..., elements[tail-1]] se -1 < head <= tail
   *      [elements[head], elements[head+1], ..., elements[elements.length-1], elements[0], ..., elements[tail-1]] se head > tail
   *
   * RI:
   *
   *  - elements is not null and has length equal to the capacity
   *  - -1 <= head < capacity
   *  -  0 <= tail < capacity
   *  - head == -1 ⇒ tail = 0
   *
   */

  /**
   * Creates a new bounded queue with the given capacity.
   *
   * @param capacity the capacity of the queue.
   * @throws IllegalArgumentException if {@code capacity} is not positive.
   */
  public BoundedIntQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be positive");
    }
    elements = new int[capacity];
    head = -1;
    tail = 0;
  }

  /**
   * Determines whether the queue is empty, i.e., it does not contain any integer.
   *
   * @return {@code true} if the queue is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    return head == -1;
  }

  /**
   * Determines whether the queue is full, i.e., it contains as many integers as its capacity.
   *
   * @return {@code true} if the queue is full, {@code false} otherwise.
   */
  public boolean isFull() {
    return tail == head;
  }

  /**
   * Returns the number of elements in the queue.
   *
   * @return the number of elements.
   */
  public int size() {
    if (isEmpty()) return 0;
    if (isFull()) return elements.length;
    return (tail - head + elements.length) % elements.length;
  }

  /**
   * Adds an element to the queue.
   *
   * @param x the element to add.
   * @throws IllegalStateException if the queue is full.
   */
  public void enqueue(int x) {
    if (isFull()) throw new IllegalStateException("the queue is full");
    if (head == -1) head = 0;
    elements[tail] = x;
    tail = (tail + 1) % elements.length;
  }

  /**
   * Removes the element at the head of the queue.
   *
   * @return the element at the head of the queue.
   * @throws NoSuchElementException if the queue is empty.
   */
  public int dequeue() {
    if (isEmpty()) throw new NoSuchElementException("La coda è vuota");
    final int r = elements[head];
    head = (head + 1) % elements.length;
    if (head == tail) {
      head = -1;
      tail = 0;
    }
    return r;
  }

  @Override
  public String toString() {
    if (isEmpty()) return "BoundedIntQueue: []";
    final StringBuilder sb = new StringBuilder("BoundedIntQueue: [");
    int i = head, n = 0;
    while (n < size() - 1) {
      sb.append(elements[i] + ", ");
      i = (i + 1) % elements.length;
      n += 1;
    }
    sb.append(elements[i] + "]");
    return sb.toString();
  }

  @Override
  public int hashCode() {
    int result = 0;
    int i = head, n = 0;
    while (n < size()) {
      result = 31 * result + Integer.hashCode(elements[i]);
      i = (i + 1) % elements.length;
      n += 1;
    }
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof BoundedIntQueue other)) return false;
    if (size() != other.size()) return false;
    int i = head, j = other.head, n = 0;
    while (n < size()) {
      if (elements[i] != other.elements[j]) return false;
      i = (i + 1) % elements.length;
      j = (j + 1) % other.elements.length;
      n += 1;
    }
    return true;
  }
}
