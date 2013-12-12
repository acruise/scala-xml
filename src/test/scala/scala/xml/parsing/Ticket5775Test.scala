package scala.xml.parsing

import org.junit.{Assert, Test}
import scala.xml.pull.{EvText, XMLEventReader}
import scala.io.Source

class Ticket5775Test {
  @Test
  def testCdataEvents() {
    val xml =
      """<?xml version="1.0"?>
        |<doc><![CDATA[This is some text]]></doc>""".stripMargin

    val evr = new XMLEventReader(Source.fromString(xml))
    while (evr.hasNext) {
      evr.next() match {
        case EvText("This is some text", false) =>
          Assert.fail("CDATA was recognized as normal text")
        case EvText("This is some text", true) => // OK!
        case other =>                             // OK!
      }
    }
  }

}
