# Autocomplete

## Run the tests
```sbt test``` to run unit tests
The tests were made with specs2
Tests are here to avoid regression during developing/refactoring

## Run the project
```sbt run``` to run the project
main class consists of micro benchmarking of two solutions on two different files containing words
1st solution: naive solution, we stream the file and select only those starting with the desired prefix
Probleme: on a bigger file filled with words beginning by A (wordsWithA.txt), searching words beginning
by P is slower.
2nd solution: build a Trie to index the words and then traverse this Trie to find autocomplete

## Comments
The question of the ordering was a bit unclear, since autocomplete("p") should return (pandora, paypal, pg&e, phone)
in the alphabetical order and not (pandora, paypal, pg&e, pinterest) like stated in the exercise.
We decided to order the autocomplete with the alphabetical order.

## Optional Questions
If the list of keyword was very large and could not fit on one machine, I would use a database specialized in full-text
search like elasticsearch.
If I had to match on part of the keywords, I would try the breadth-first search method first. Traversing the whole Trie
to find the corresponding keyword parts.
