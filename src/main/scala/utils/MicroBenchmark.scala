package utils

import tries.Trie
import tries.TrieNode

object MicroBenchmark {
  /** Print the time taken by the block without interrupting the flow
   * 
   *  @param block portion of code we want to mesure
   *  @return the result of the execution of the block 
   *
   */
  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println(f"Elapsed time: ${(t1 - t0).toDouble/(1000000)}%.3f ms")
    result
  }

  /** Auxilliary function for meanTime
   */
  def time2[R](block: => R): (Long,R) = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    (t1 - t0, result)
  }

  /** Print the mean time taken by the block executed i times without interrupting the flow
   *
   *  @param block portion of code we want to mesure
   *  @param i number of time we execute the blocl
   *  @return the result of the last execution of the block
   *
   */
  def meanTime[R](i: Int)(block: => R): R = {
    val sum = (0 to i).map(x => time2(block))
                      .fold((0L,null))((x,y) => (x._1+y._1,y._2))

    println(f"Elapsed mean time on $i results: ${sum._1.toDouble/(i*1000000)}%.3f ms")

    sum._2.asInstanceOf[R]
  }

}
