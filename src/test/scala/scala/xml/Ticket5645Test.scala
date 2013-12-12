package scala.xml

import org.junit.{Assert, Test}
import scala.xml.parsing.ConstructingParser

class Ticket5645Test {
  @Test
  def testSuppressEscapingInTextNodes() {
    val xml = """<script type="text/javascript">
                |  foo("{bar}");
                |</script>""".stripMargin

    val cp = new ConstructingParser(io.Source.fromString(xml), true)
    cp.initialize

    val elem = cp.document().docElem.asInstanceOf[Elem]
    Assert.assertTrue("A double quote character was needlessly escaped in a text node", !elem.toString().contains("&quot;"))
  }
}
