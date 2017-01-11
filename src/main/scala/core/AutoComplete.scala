package core

import tries.Trie
import tries.TrieNode

/** object containing functions to build 
 * and launch the two autocompletes implemented
 *
 */
object AutoComplete {
  /** Perform a naive autocomplete
   * 
   *  @param stream words we base our autocomplete on
   *  @param n number of autocomplete results
   *  @param str input we want to autocomplete
   *  @return list of words composing the autocomplete
   * 
   */
  def autoComplete1(stream: Stream[String])(n: Int)(str: String): List[String] = {
    stream.map(_.toLowerCase)
          .filter(x => x.startsWith(str.toLowerCase))
          .take(n)
          .toList
          .sorted
  }

  /** Perform a Trie based autocomplete                     
   *
   *  @param trie our index we built on the word file
   *  @param n number of autocomplete results
   *  @param str input we want to autocomplete
   *  @return list of words composing the autocomplete
   *
   */
  def autoComplete2(trie: TrieNode)(n: Int)(str: String): Seq[String] = {
    import tries._

    //we use implicit classes of the package object tries
    //for a clearer syntax
    trie.searchPrefix(str.toLowerCase)
        .flattenTriePrefixed(str)(n)
  }

  /** Build a Trie (index) from a file containing words
   * 
   *  @param fileName file location
   *  @return trie containing all the words 
   *  
   */
  def buildTrie(fileName: String): TrieNode = {
    import Trie._
    import utils.FileToStream._
    val s = sourceFromFile(fileName)
    var builtTrie = zero
    for (elem <- s) {
      builtTrie = loadRecursive(builtTrie, "^" + elem.toLowerCase + "$")
    }
    builtTrie
  }
} 

