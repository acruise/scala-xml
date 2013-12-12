package scala.xml.parsing

import org.junit.{Assert, Test}

class Ticket7796Test {
  @Test
  def testEventReaderAposEntity() {
    import scala.xml.pull._
    import scala.io.Source

    val src = Source.fromString("<test>&amp;&lt;&gt;&apos;&quot;</test>")
    val er = new XMLEventReader(src)

    while (er.hasNext) {
      er.next() match {
        case EvComment(s) => Assert.fail("Unhandled apos entity")
        case _ => ()
      }
    }
  }
}
