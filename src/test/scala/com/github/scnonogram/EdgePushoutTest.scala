package com.github.scnonogram

import com.github.scnonogram.rule.EdgePushout
import org.junit.Test

class EdgePushoutTest extends BaseTest {

  @Test
  def test_edge_1() = {

    val line = emptyLine(25)

    addBlock( line, 6 )

    line.fill(3)

    EdgePushout.apply(line)

    assertUnsolved(line, List(0,1,2))
    assertUnsolved(line, List( 6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24))
    assertFilled(line, List(3,4,5))
  }


}
