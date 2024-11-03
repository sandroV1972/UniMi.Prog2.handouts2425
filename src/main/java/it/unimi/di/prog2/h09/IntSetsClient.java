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

package it.unimi.di.prog2.h09;

import java.util.ArrayList;
import java.util.List;

/** A class to test some methods of {@link IntSet}. */
public class IntSetsClient {

  /** . */
  private IntSetsClient() {}

  /**
   * Tests some methods of {@link IntSet}.
   *
   * <p>Given a list of integers as arguments on the command line, builds a set from them, and then
   * for every element in the list, emits <samp>list</samp> if the element is in the list, then for
   * every element in the set, emits an <samp>set</samp> if the element is in the list.
   *
   * @param args the integers to be used as elements of the list.
   */
  public static void main(String args[]) {
    List<Integer> list = new ArrayList<>();
    IntSet set = new IntSet();
    for (String s : args) {
      int x = Integer.parseInt(s);
      list.add(x);
      set.insert(x);
    }
    for (int x : list) if (set.isIn(x)) System.out.println("list");
    while (set.size() > 0) {
      int x = set.choose();
      if (list.indexOf(x) != -1) System.out.println("set");
      set.remove(x);
    }
  }
}
