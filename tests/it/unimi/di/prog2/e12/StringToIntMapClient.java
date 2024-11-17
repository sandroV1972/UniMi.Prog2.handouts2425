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

package it.unimi.di.prog2.e12;

/** A class to exercise some methods of {@link StringToIntMap}. */
public class StringToIntMapClient {

  /** . */
  private StringToIntMapClient() {}

  /**
   * A simple client to test the {@link StringToIntMap} class.
   *
   * <p>The client reads lines from the standard input, where each line is a command followed by
   * some arguments. The commands are:
   *
   * <ul>
   *   <li>{@code + key value} to add a key-value pair to the map;
   *   <li>{@code - key} to remove the key from the map;
   *   <li>{@code ? key} to get the value associated to the key;
   *   <li>{@code c} to clear the map;
   *   <li>{@code #} to get the size of the map.
   * </ul>
   *
   * <p>The client emits in the standard output the value returned by performing the action
   * requested by the commands.
   *
   * @param args not used.
   */

  /*- Uncomment the following code once you have implemented the StringToIntMap class.

  public static void main(String[] args) {
    StringToIntMap map = new StringToIntMap();
    try (Scanner s = new Scanner(System.in)) {
      while (s.hasNextLine()) {
        String[] cmds = s.nextLine().split(" ");
        switch (cmds[0].charAt(0)) {
          case '+':
            System.out.println(map.put(cmds[1], Integer.parseInt(cmds[2])));
            break;
          case '-':
            System.out.println(map.remove(cmds[1]));
            break;
          case '?':
            System.out.println(map.get(cmds[1]));
            break;
          case 'c':
            map.clear();
            break;
          case '#':
            System.out.println(map.size());
            break;
        }
      }
    }
  }

  */
}
