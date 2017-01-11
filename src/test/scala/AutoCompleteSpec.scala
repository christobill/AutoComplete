

class AutoCompleteSpec extends org.specs2.mutable.Specification {
  import tries._
  import core.AutoComplete._

  "Autocomplete with trie should" >> {
    "answer with 1 word if 1 word in the list begins with specified prefix" >> {

      val prefix = "cccccc"

      val trie = buildTrie("src/test/resources/test.txt")
      val result = trie.searchPrefix(prefix)
                       .flattenTriePrefixed(prefix)(4)

      result === Vector("cccccc")
    }

    "answer with 2 words if 2 words in the list begins with specified prefix" >> {
      val prefix = "aaaaa"

      val trie = buildTrie("src/test/resources/test.txt")
      val result = trie.searchPrefix(prefix)
                       .flattenTriePrefixed(prefix)(4)

      result === Vector("aaaaaaa", "aaaaaab")
    }

    "if no word in the list begin with the specified prefix, return empty Seq" >> {
      val prefix = "aaaaaaaaaaaaaaaaaaaaa"

      val trie = buildTrie("src/test/resources/test.txt")
      val result = trie.searchPrefix(prefix)
                       .flattenTriePrefixed(prefix)(4)

      result === Vector.empty[String]
    }

    "respect the ordering and the number of elements in the autocomplete" >> {
      val prefix = "prog"

      val trie = buildTrie("src/main/resources/words.txt")
      val result = trie.searchPrefix(prefix)
                       .flattenTriePrefixed(prefix)(4)

      result.toSeq === Vector("progenex", "progeria", "progesterone", "programming")
    }
  }

}
