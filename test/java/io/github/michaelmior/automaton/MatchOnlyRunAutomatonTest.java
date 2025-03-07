/*
 * io.github.michaelmior.automaton
 *
 * Copyright (c) 2001-2017 Anders Moeller
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package io.github.michaelmior.automaton;

import static io.github.michaelmior.automaton.RunAutomatonTest.NOT_MATCHED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/** Tests {@link MatchOnlyRunAutomaton}. */
final class MatchOnlyRunAutomatonTest {

  private final DatatypesAutomatonProvider automatonProvider =
      new DatatypesAutomatonProvider(true, true, true);

  @MethodSource("io.github.michaelmior.automaton.RunAutomatonTest#runScenarios")
  @ParameterizedTest
  void matches_substring_as_expected(
      final int posStart, final int posEnd, final RegExp regex, final String input) {

    // Do not append anything to the input for expected non-matches, as these might then match
    // For matching cases we pad the string before and after to test substring matching
    final String inputWithPreAndPostfix =
        posStart == NOT_MATCHED ? input : PREFIX_TEXT + input + POSTFIX_TEXT;

    final Automaton automaton = regex.toAutomaton(automatonProvider, true);
    final boolean expectedMatch = posStart != NOT_MATCHED;

    final MatchOnlyRunAutomaton noTableize = new MatchOnlyRunAutomaton(automaton, false);
    assertEquals(expectedMatch, noTableize.matches(inputWithPreAndPostfix));

    final int posToStart = expectedMatch ? PREFIX_TEXT.length() - 1 : 0;

    // Match from pos
    assertEquals(expectedMatch, noTableize.matches(inputWithPreAndPostfix, posToStart));

    final MatchOnlyRunAutomaton tableize = new MatchOnlyRunAutomaton(automaton, true);
    assertEquals(expectedMatch, tableize.matches(input));
    // Match from pos
    assertEquals(expectedMatch, tableize.matches(inputWithPreAndPostfix, posToStart));
  }

  @Test
  void handles_invalid_inputs() {
    final MatchOnlyRunAutomaton testee = new MatchOnlyRunAutomaton(new RegExp(".*").toAutomaton());

    // null input
    assertFalse(testee.matches(null));
    // pos outside string
    assertThrows(IllegalArgumentException.class, () -> testee.matches("123", 3));
    // negative pos
    assertThrows(IllegalArgumentException.class, () -> testee.matches("123", -1));
  }

  @Test
  void stores_and_loads_as_expected(@TempDir final File tmpdir)
      throws IOException, ClassNotFoundException {

    final Automaton automaton = new RegExp("12345.+").toAutomaton();
    final MatchOnlyRunAutomaton testee = new MatchOnlyRunAutomaton(automaton);
    final String input = "12345abc";

    assertTrue(testee.matches(input));

    // Store
    final File file = new File(tmpdir, "serialized");
    try (final FileOutputStream fos = new FileOutputStream(file)) {
      testee.store(fos);
    }

    // Load
    final MatchOnlyRunAutomaton deserialized;
    try (final FileInputStream fis = new FileInputStream(file)) {
      deserialized = MatchOnlyRunAutomaton.load(fis);
    }

    assertNotNull(deserialized);
    assertTrue(deserialized.matches(input));
  }

  private static final String PREFIX_TEXT = "$£ndmd ";
  private static final String POSTFIX_TEXT = " a+;@|Zn";
}
