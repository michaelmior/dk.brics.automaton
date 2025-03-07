# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.12-4] 2022-01-03
### Changed
- re-enable Java 8 support - thanks to D. Lowe

## [1.12-3] 2021-08-11
### Changed
- upgrade to Java 11 - thanks to L.J. McGibbney

## [1.12-2] 2017-09-14
### Fixed
- fixed `RegExp.toString` - thanks to A. Gargantini

## [1.12-1] 2017-04-07
### Added
- added Valmari's minimization algorithm - contributed by S. Gregersen

### Fixed
- fixed missing check of 'minimize' option - thanks to V. Wuestholz
- fixed state naming for singleton automata - thanks to A.M.W. Younang and S. Gregersen

### Changed
- now supports Maven - thanks to S. Gregersen
- `RunAutomaton.setAlphabet` now non-final - suggested by D. Lowe
- minor simplification of `determinize` - suggested by V. Berchet

## 1.11-8
### Changed
- caching of `isDebug`, to avoid synchronized call to `System.getProperty` - thanks to G. Lundh

## 1.11-7
### Fixed
- bug fix in Gibson's AutomatonMatcher - thanks to Y. Versley, D. Weiss, and D. Richardson

## 1.11-6
### Changed
- performance improvement in `Automaton.isFinite` - thanks to R. Muir

## 1.11-5
### Fixed
- bug fix in Gibson's `AutomatonMatcher` - thanks to H-.M. Adorf and J. Gibson

## 1.11-4
### Fixed
- bug fix and performance improvement in `BasicOperations.concatenate` - thanks to R. Muir

## 1.11-2
### Added
- added `Automaton.makeStringUnion` (Daciuk et al.'s algorithm for constructing 
  a minimal automaton that accepts a union of strings) - contributed by D. Weiss
- added call to `clearHashCode` in `Automaton.reduce` - thanks to D. Weiss 

### Changed
- minimization is now optional in `RegExp.toAutomaton` - suggested by H. Zauner
- `SpecialOperations.reverse` made public - suggested by D. Lowe

## 1.11-2
### Fixed
- fixed bug in `RegExp` parser - thanks to A. Meyer

## 1.11-1
### Added
- added `AutomatonMatcher` - contributed by J. Gibson

### Fixed
- fixed bug in `SpecialOperations.overlap` - thanks to D. Lutterkort

## 1.10-5
### Added
- added `RegExp.setAllowMutate` (for thread safety)

## 1.10-4
### Fixed
- fixed bug in recomputation of Automaton hash code

## 1.10-3
### Added
- added Automaton method: `getStrings`
- added `setAllowMutate` method that controls whether operations are allowed
  to modify input automata

### Fixed
- fixed bug in regexp parser
- improved javadoc description of automaton representation

## 1.10-2
### Fixed
- fixed bug in repeat(int,int) that was introduced in 1.9-1 - thanks to B. Lee

## 1.10-1
### Added
- added `Datatypes` class with lots of common regular languages 
  (was earlier placed in the dk.brics.automaton.schematools package)
- added `DatatypesAutomatonProvider` class so that regexps easily can use the datatypes
- added Automaton methods: 
    - `makeMaxInteger`
    - `makeMinInteger`
    - `makeTotalDigits`
    - `makeFractionDigits`
    - `makeIntegerValue`
    - `makeDecimalValue`
    - `makeStringMatcher`
    - `prefixClose`
    - `hexCases`
    - `replaceWhitespace`

### Fixed
- fixed bug in `subsetOf` for `nondeterministic` automata

### Changed
- now allowing the empty regexp (which matches the empty string) in `RegExp` syntax
- intersection now works on nondeterministic automata without determinizing
- rewritten `getShortestExample`
- removed Makefile (use 'ant' instead)

## 1.9-1
### Changed
- moved Automaton methods to other classes (`BasicAutomata`, `BasicOperations`, etc.)
- `Automaton.toString` now prints singleton automata as strings without expanding them
- `Automaton.subsetOf` rewritten to make it faster
- `Automaton.run` now works on nondeterministic automata without determinizing

## 1.8-8
### Fixed
- fixed bug in `RunAutomaton.run(String, int)` - thanks to J. Moran

## 1.8-7
### Added
- added AutomatonProvider for RegExp.toAutomaton

## 1.8-6
### Fixed
- fixed bug in `subst(Map)`

## 1.8-5
### Added
- added `Automaton` method: `overlap`

### Fixed
- fixed bug in `concatenate(List<Automaton>)`

## 1.8-4
### Changed
- faster singleton mode for various automata operations

### Added
- added Automaton methods: 
    - `minus`
    - `shuffleSubsetOf`
    - `isEmptyString`

## 1.8-3
### Changed
- faster construction of automata from large regexps
- improved performance of `Automaton.repeat(int)` and `Automaton.repeat(int, int)`

## 1.8-2
### Fixed
- fixed bug in `makeInterval` (for non-fixed number of digits) - thanks to A. Bakic

## 1.8-1
### Changed
- shifted to Java 5
- reorganized source

### Added
- added `Automaton` method: `subst(char,String)`

## 1.7-1
### Added
- added Hopcroft's minimization algorithm (more predictable 
  behavior than Brzozowski's, but typically a bit slower)

## 1.6-6
### Fixed
- fixed bug in complement of nondeterministic automata

## 1.6-5
### Added
- added `Automaton` method: `subst`

## 1.6-4
### Changed
- changed to BSD license

## 1.6-3
### Added
- added `shuffle` (interleaving) operation to `Automato`n

## 1.6-2
### Fixed
- fixed bug in `Automaton.concatenate(List)`

## 1.6-1
### Added
- added numerical intervals to `RegExp` and Automaton

## 1.5-1
### Added
- added Brzozowski's minimization algorithm

### 1.4-1
### Added
- special fast mode for single string automata
- added `Automaton` method: `restoreInvariant`

## 1.3-3
### Added
- added Automaton methods: 
    - `hashCode` (needed by `equals`)
    - `getCommonPrefix`

## 1.3-2
### Added
- added Automaton methods:
    - `getShortestExample`
    - `setMinimizeAlways`

## 1.3-1
### Changed
- `State` class, `Transition` class, and various `Automaton` methods made public
  to allow manual construction of automata

## 1.2-7
### Added
- added `Automaton` method: `compress`

## 1.2-6
### Added
- added `build.xml` makefile for Ant

## 1.2-5
### Added
- added `Automaton` methods:
    - `equals`
    - `load` (from stream or URL)
    - `store` (to stream)

### Changed
- `Automaton` implements `Serializable`

## 1.2-4
### Added
- added `Automaton` methods:
    - `projectChars`
    - `concatenate` (`List`)
    - `union` (`List`)

## 1.2-3
### Added
- added Automaton methods:
    - `trim`
    - `homomorph`
    - `singleChars`
    - `isEmpty`
    - `isTotal`
    - `isFinite`
    - `getFiniteStrings`
    - `getNumberOfStates`
    - `getNumberOfTransitions`
    - `subsetOf`
- added `RunAutomaton` methods:
    - load (from stream or URL)
    - store (to stream)
    - run (longest accepted substring run)
- added `getIdentifiers` method to `RegExp`

### Changed
- `RunAutomaton` implements `Serializable`

## 1.1
### Added
- added regular expression parser (`RegExp` class)

### Changed
- tableized run method in `RunAutomaton`

[Unreleased]: https://github.com/michaelmior/dk.brics.automaton/compare/v1.12-4...HEAD
[1.12-4]: https://github.com/michaelmior/dk.brics.automaton/compare/v1.12-3...v1.12-4
[1.12-3]: https://github.com/michaelmior/dk.brics.automaton/compare/v1.12-2...v1.12-3
[1.12-2]: https://github.com/michaelmior/dk.brics.automaton/compare/v1.12-1...v1.12-2
[1.12-1]: https://github.com/michaelmior/dk.brics.automaton/releases/tag/v1.12-1
