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

package it.unimi.di.prog2.e18;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Allows verification of the behavior of an astronomical system.
 *
 * <p>For more details, see the <em>overview</em> of this package.
 */
public class AstronomicalSystemClient {

  /** . */
  private AstronomicalSystemClient() {}

  /**
   * Simulates an astronomical system.
   *
   * <p>Reads the information of celestial bodies from standard input, and simulates the system for
   * the number of steps specified as the first argument on the command line; hence it emits on the
   * standard error the state of the system and the total energy.
   *
   * @param args the number of simulation steps.
   */


  public static void main(String[] args) {
   if (args.length != 1) {
      System.err.println("Usage: java AstronomicalSystemClient <number_of_steps>");
      System.exit(1);
    }

    int steps;
    try {
      steps = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      System.err.println("The number of steps must be an integer.");
      System.exit(1);
      return; // Questo return è necessario solo per evitare un errore di compilazione.
    }

    List<CelestialBody> bodies = new ArrayList<>();
    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] parts = line.split("\\s+");
        if (parts.length != 5) {
          System.err.println("Invalid input format. Each line must contain 5 values: type, name, x, y, z");
          System.exit(1);
        }
        String type = parts[0];
        String name = parts[1];
        int x = Integer.parseInt(parts[2]);
        int y = Integer.parseInt(parts[3]);
        int z = Integer.parseInt(parts[4]);
        if (type.equals("P")) {         
          bodies.add(new Planet(name, new Point(x, y, z)));
        } else {
          bodies.add(new Star(name, new Point(x, y, z)));
        }
      }
    }

    AstronomicalSystem system = new AstronomicalSystem(bodies);
    for (int i = 0; i < steps; i++) {
      system.simulateStep();
    }

    System.out.println(system);
  }

}
