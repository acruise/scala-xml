package scala.xml.parsing

import org.junit.{Assert, Test}

class Ticket7282Test {
  @Test
  def testSourcesAreClosed() {
    val xml =
      """<?xml version="1.0"?>
        |<hi there="here"/>""".stripMargin

    var closed = false

    def source = new scala.io.Source() {
      protected val iter: Iterator[Char] = xml.iterator
      override def close() {
        super.close()
        closed = true
      }
    }

    val cp = new ConstructingParser(source, true)
    cp.initialize
    val doc = cp.document()
    println(doc)

    Assert.assertTrue("Source was not closed", closed)
  }
}
