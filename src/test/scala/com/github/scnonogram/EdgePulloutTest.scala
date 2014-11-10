package com.github.scnonogram

import com.github.scnonogram.rule.EdgePullout
import org.junit.Test

class EdgePulloutTest extends BaseTest {

  @Test
  def test_edgepullout_1() = {

    val line = emptyLine(25)

    line.fill(3)
    line.fill(4)
    line.fill(5)
    line.fill(6)
    line.fill(7)

    addBlock(line,7)

    EdgePullout.apply(line)

    assertBlank(line, List(0) )
    assertUnsolved(line, List(1,2))
    assertFilled(line, List(3,4,5,6,7))
    assertUnsolved(line, List(8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24) )

  }

  @Test
  def test_edgepullout_2() = {

    val line = emptyLine(25)

    line.fill(12)
    line.fill(13)
    line.fill(14)
    line.fill(15)

    addBlock(line,10)

    EdgePullout.apply(line)

    assertUnsolved(line, List(1,2,3,4,5,6,7,8,9,10,11) )
    assertFilled(line, List(12,13,14,15) )
    assertUnsolved(line, List(16,17,18,19,20,21) )
    assertBlank(line, List(22,23,24) )

  }
}
