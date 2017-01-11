
/** 
 * package object for simpler syntax
 * using implicit conversions 
 *
 */
package object tries {
  implicit class ImplicitTries0(trie: TrieNode) {
    def searchPrefix(str: String): Option[TrieNode] = {
      Trie.searchPrefix(Some(trie),"^" + str.toLowerCase + "$") 
    } 
  }
  implicit class ImplicitTries1(optTrie: Option[TrieNode]) {
    def flattenTriePrefixed(prefix: String)(n: Int): Seq[String] = {
      Trie.flattenTriePrefixed(prefix)(n)(optTrie)
    }

  }
}
