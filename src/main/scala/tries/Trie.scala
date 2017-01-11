package tries

import scala.annotation.tailrec

sealed trait Trie
case class TrieNode(children: Map[Char,TrieNode]) extends Trie

/** object containings functions to build
 *  and search recursively in the trie
 *
 */
object Trie {
  def zero = TrieNode(Map.empty[Char,TrieNode])

  /** load word in a Trie
   *
   *  @param node TrieNode to build on
   *  @param str string to add
   *  @return updated TrieNode
   *
   */
  def loadRecursive(node: TrieNode, str: String): TrieNode = {
    str(0) match {
      case s0: Char if s0 == '$' => TrieNode(Map.empty[Char,TrieNode])
      case s0: Char => {
                         val trieRec: TrieNode = node.children.getOrElse(s0, zero)
                         val map = node.children + (s0 -> loadRecursive(trieRec,str.tail))
                         TrieNode(map)
                       }
    }
  }

  /** find prefix in a Trie
   *
   *  @param str prefix to find in the Trie
   *  @param node 
   *  @return the TrieNode associated with the prefix
   *
   */
  def searchPrefix(node: Option[TrieNode], str: String): Option[TrieNode] = {
    
    str(0) match {
      case s0: Char if s0 == '$' => node
      case s0: Char => {
                         val trieRec: Option[TrieNode] = node.flatMap(_.children.get(s0))
                         searchPrefix(trieRec,str.tail)
                       }
    }
  }

  //this is an implicit def used in flattenTriePrefixed0 and flattenTriePrefixed1 to sort the Map elements by keys
  implicit def orderingByName[A <: (Char, tries.TrieNode)]: Ordering[A] =
    Ordering.by(e => e._1)

  //simpler/previous version of flattenTriePrefixed1
  def flattenTriePrefixed0(acc: String)(result: TrieNode): Stream[String] = {
    result match {
      case trie: TrieNode if trie.children.isEmpty => Stream(acc)
      case trie: TrieNode => trie.children.toStream.sorted.flatMap(x => flattenTriePrefixed0(acc + x._1)(x._2))
    }
  }

  /** flatten a TrieNode
   * 
   *  @param acc used 
   *  @param n number of autocomplete results 
   *  @param result TrieNode to flatten
   *  @return a Stream[String] containing all the words in the TrieNode 
   *         
   */
  def flattenTriePrefixed1(acc: String)(n: Int)(result: TrieNode): Stream[String] = {
    result match {
      case trie: TrieNode if trie.children.isEmpty => Stream(acc)
      case trie: TrieNode => trie.children.toStream.sorted.take(n).flatMap(x => flattenTriePrefixed1(acc + x._1)(n)(x._2)).take(n)
    }
  }

  /** extensions of flattenTriePrefixed1 to handle the Option[TrieNode]
   *
   *  @param acc used
   *  @param n number of autocomplete results
   *  @param result TrieNode to flatten
   *  @return a Stream[String] containing all the words in the TrieNode
   *                                     
   */
  def flattenTriePrefixed(acc: String)(n: Int)(result: Option[TrieNode]): Stream[String] = {
    result.map(flattenTriePrefixed1(acc)(n)(_).take(n)).getOrElse(Stream())
  }

}

