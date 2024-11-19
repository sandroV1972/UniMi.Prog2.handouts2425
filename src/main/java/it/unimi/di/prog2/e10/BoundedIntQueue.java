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

package it.unimi.di.prog2.e10;

import java.util.Arrays;
import java.util.Objects;

/**
 * A <em>queue</em> is a mutable data structure that provides access to its elements in
 * first-in/first-out order.
 *
 * <p>A <em>bounded</em> queue has an upper bound, established when a queue is created, on the
 * number of elements that can be stored in the queue.
 */
public class BoundedIntQueue {

  // EXERCISE: complete following the specification (with particular attention
  // to the eventual exceptions) and provide an implementation (including the
  // equals, hashCode, and toString methods); add methods that are adequate to
  // the specification.

  // Given the boundedness constraint, it is not allowed to use any Java
  // Collection Framework class. An array can be used to store the elements in a
  // circular buffer (see https://www.wikiwand.com/en/articles/Circular_buffer).

  private int[] queue;
  private int capacity;
  private int size;
  private int front;
  private int rear;

  /**
   * Creates a new bounded queue with the given capacity.
   *
   * @param capacity the capacity of the queue.
   * @throws IllegalArgumentException if {@code capacity} is negative.
   */
  public BoundedIntQueue(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity must be 0 or positive");
    }
    this.capacity = capacity;
    this.queue = new int[capacity];
    this.front = 0;
    this.rear = 0;
    this.size = 0;
  }

  /**
   * Adds an element to the queue.
   *
   * @param x the element to add.
   * @throws IllegalStateException if the queue is full.
   */
  public void enqueue(int x) {
    if (this.isFull()) {
      throw new IllegalStateException("The queue is already full");
    }
    queue[this.getFront()] = x;
    this.front = (this.getFront() + 1) % this.getCapacity();
    this.size++;

  }

  /**
   * Retrieve the size of the queue.
   *
   * @param none
   * 
   */
  public boolean isFull() {
    //REQUIRES nothing
    //MODIFY nothing
    //ESPECT true if the size == capacity
    return this.getSize() == this.getCapacity();
  }

    /**
   * Retrieve the size of the queue.
   *
   * @param none
   * 
   */
  public boolean isEmpty() {
    //REQUIRES nothing
    //MODIFY nothing
    //ESPECT true if the size == 0
    return this.getSize() == 0;
  }

  /**
   * Increase the size of the queue.
   *
   * @param none
   * 
   */
  public void queueIncreaseSize() {
    //REQUIRES nothing
    //MODIFY add 1 to the queue size
    //ESPECT increase size
    this.size += 1;
  }

  /**
   * Decrease the size of the queue.
   *
   * @param none
   * 
   */
  public void queueDecreaseSize() {
    //REQUIRES nothing
    //MODIFY subtract 1 to the queue size
    //ESPECT decrease size
    this.size -= 1;
  }

  /**
   * Retrieve the size of the queue.
   *
   * @param none
   * 
   */
  public int getSize() {
    //REQUIRES nothing
    //MODIFY nothing
    //ESPECT returns the number of elements in the queue
    return this.size;
  }

  /**
   * Retrieve the front of the queue.
   *
   * @param none
   * 
   */
  public int getFront() {
    //REQUIRES nothing
    //MODIFY nothing
    //ESPECT returns the index of the front the queue
    return this.front;
  }

    /**
   * Retrieve the front of the queue.
   *
   * @param none
   * 
   */
  public void setFront(int f) {
    //REQUIRES nothing
    //MODIFY nothing
    //ESPECT returns the index of the front the queue
    this.front = f;
  }

    /**
   * Retrieve the front of the queue.
   *
   * @param none
   * 
   */
  public int getRear() {
    //REQUIRES nothing
    //MODIFY nothing
    //ESPECT returns the index of the rear of the queue
    return this.rear;
  }

    /**
   * Retrieve the front of the queue.
   *
   * @param none
   * 
   */
  public void setRear(int r) {
    //REQUIRES nothing
    //MODIFY the index of the rear of the queue
    //ESPECT nothing
    this.rear = r;
  }

  /**
   * Retrieve the capcity of the queue.
   *
   * @param none
   * 
   */
  public int getCapacity() {
    //REQUIRES nothing
    //MODIFY nothing
    //ESPECT returns the capacity of the queue
    return this.capacity;
  }

  /**
   * Removes the element at the head of the queue.
   *
   * @return the element at the head of the queue.
   * @throws IllegalStateException if the queue is empty.
   */
  public int dequeue() {
    if (this.isEmpty()) {
      throw new IllegalStateException("You are trying to remove something from an empty queue");
    }
    int r = this.queue[this.getRear()];
    this.queue[this.getRear()] = 0;
    this.rear = (this.rear + 1) % this.capacity;
    this.queueDecreaseSize();
    return r;
  }
    /**
   * Restituisce una rappresentazione stringa della coda.
   *
   * @return una stringa che rappresenta la coda.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("BoundedIntQueue: [");
    for (int i = 0; i < size; i++) {
      sb.append(queue[(rear + i) % capacity]);
      if (i < size - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * Verifica se questo oggetto è uguale a un altro oggetto.
   *
   * @param o l'oggetto da confrontare.
   * @return true se gli oggetti sono uguali, false altrimenti.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BoundedIntQueue that = (BoundedIntQueue) o;
    return capacity == that.capacity &&
           size == that.size &&
           front == that.front &&
           rear == that.rear &&
           Arrays.equals(queue, that.queue);
  }

  /**
   * Restituisce il codice hash di questo oggetto.
   *
   * @return il codice hash di questo oggetto.
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(capacity, size, front, rear);
    result = 31 * result + Arrays.hashCode(queue);
    return result;
  }
}
