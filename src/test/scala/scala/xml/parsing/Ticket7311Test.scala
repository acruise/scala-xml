package scala.xml.parsing

import org.junit.Test

class Ticket7311Test {
  @Test(expected=classOf[scala.xml.parsing.FatalError])
  def testInvalidSyntax(): Unit = {
    ConstructingParser.fromSource(scala.io.Source.fromString("<node attr=\""), preserveWS=true).document().docElem
  }
}
