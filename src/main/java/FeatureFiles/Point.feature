Feature: Point
 Scenario: Check symbols appear correctly
 Given a word has a valid symbol, or valid symbols
 When a user hovers over a word
 And after a short delay
 Then the word should be highlighted
 And a symbol, or multiple symbols, should appear for that context