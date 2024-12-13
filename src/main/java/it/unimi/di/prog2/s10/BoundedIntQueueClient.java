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

/** A class to exercise a {@link BoundedIntQueue}. */
public class BoundedIntQueueClient {

  /** . */
  private BoundedIntQueueClient() {}

  /**
   * Tests some methods of {@link BoundedIntQueue}.
   *
   * <p>Reads a list of integers from the standard input; if a positive number is read, it is added
   * to a bounded queue of size 10; if a negative number is read, it is dequeued and emitted on the
   * standard output. When the input terminates, the queue is emitted on the standard output.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    BoundedIntQueue queue = new BoundedIntQueue(10);
    try (java.util.Scanner s = new java.util.Scanner(System.in)) {
      while (s.hasNextInt()) {
        int x = s.nextInt();
        if (x > 0) queue.enqueue(x);
        else System.out.println(queue.dequeue());
      }
    }
    System.out.println(queue);
  }
}
