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

import it.unimi.di.prog2.jubbiot.BlackBoxTestsGenerator;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

public class JubbiotTest {

  @TestFactory
  public List<? extends DynamicNode> testAll() throws IOException {
    return new BlackBoxTestsGenerator("tests").generate("it.unimi.di.prog2");
  }
}
