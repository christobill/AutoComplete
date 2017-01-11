import tries.Trie
import tries.TrieNode

//Main Class
object RunAutoComplete extends App {
  override def main(args: Array[String]) = {
    import utils.MicroBenchmark._
    import utils.FileToStream._
    import core.AutoComplete._    

    //RunAutoComplete parameters
    val numSimulation = 1000
    val numResultsAutoC = 4
    val wordsFile = "src/main/resources/words.txt"
    val wordsWithAFile = "src/main/resources/wordsWithA.txt"

    println("\nShort file, naive autocomplete :")
    println("==================================")
    println("result for autocomplete('p') : " + meanTime(numSimulation)(autoComplete1(sourceFromFile(wordsFile))(numResultsAutoC)("p")))
    println("result for autocomplete('pr') : " + meanTime(numSimulation)(autoComplete1(sourceFromFile(wordsFile))(numResultsAutoC)("pr")))
    println("result for autocomplete('pro') : " + meanTime(numSimulation)(autoComplete1(sourceFromFile(wordsFile))(numResultsAutoC)("pro")))
    println("result for autocomplete('prog') : " + meanTime(numSimulation)(autoComplete1(sourceFromFile(wordsFile))(numResultsAutoC)("prog")))
    println("==================================\n")

    println("\nBigger file, naive autocomplete :")
    println("===================================")
    println("result for autocomplete('p') : " + meanTime(numSimulation)(autoComplete1(sourceFromFile(wordsWithAFile))(numResultsAutoC)("p")))
    println("result for autocomplete('pr') : " + meanTime(numSimulation)(autoComplete1(sourceFromFile(wordsWithAFile))(numResultsAutoC)("pr")))
    println("result for autocomplete('pro') : " + meanTime(numSimulation)(autoComplete1(sourceFromFile(wordsWithAFile))(numResultsAutoC)("pro")))
    println("result for autocomplete('prog') : " + meanTime(numSimulation)(autoComplete1(sourceFromFile(wordsWithAFile))(numResultsAutoC)("prog")))    
    println("===================================\n")

    println("\nShort file, trie autocomplete :")
    val trie1 = buildTrie(wordsFile)
    println("=================================")
    println("result for autocomplete('p') : " + meanTime(numSimulation)(autoComplete2(trie1)(numResultsAutoC)("p")).toList)
    println("result for autocomplete('pr') : " + meanTime(numSimulation)(autoComplete2(trie1)(numResultsAutoC)("pr")).toList)
    println("result for autocomplete('pro') : " + meanTime(numSimulation)(autoComplete2(trie1)(numResultsAutoC)("pro")).toList)
    println("result for autocomplete('prog') : " + meanTime(numSimulation)(autoComplete2(trie1)(numResultsAutoC)("prog")).toList)
    println("=================================\n")
   
    println("\nBigger file, trie autocomplete :")
    val trie2 = buildTrie(wordsWithAFile)
    println("==================================")
    println("result for autocomplete('p') : " + meanTime(numSimulation)(autoComplete2(trie2)(numResultsAutoC)("p")).toList)
    println("result for autocomplete('pr') : " + meanTime(numSimulation)(autoComplete2(trie2)(numResultsAutoC)("pr")).toList)
    println("result for autocomplete('pro') : " + meanTime(numSimulation)(autoComplete2(trie2)(numResultsAutoC)("pro")).toList)
    println("result for autocomplete('prog') : " + meanTime(numSimulation)(autoComplete2(trie2)(numResultsAutoC)("prog")).toList)
    println("==================================\n")
  }

}
