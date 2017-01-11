package utils

object FileToStream {

  /** Creates Stream from a local file
   * 
   *  @param fileName name of the file to load
   *  @return Stream of the words contained in the file
   *  
   */
  def sourceFromFile(fileName: String): Stream[String] = {
    import scala.io.Source
    Source.fromFile(fileName)
          .getLines()
          .toStream
  }

} 

