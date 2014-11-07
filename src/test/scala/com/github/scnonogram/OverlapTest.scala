package com.github.scnonogram

import com.github.scnonogram.rule.Overlap
import org.junit.Test

class OverlapTest extends BaseTest {

  @Test
  def test_overlap_1() = {

    val line = emptyLine(25)

    addBlock( line, 16)

    Overlap.apply(line)

    assertUnsolved( line, List(0,1,2,3,4,5,6,7,8))
    assertUnsolved( line, List(16,17,18,19,20,21,22,23,24))
    assertFilled( line, List( 9,10,11,12,13,14,15))
  }

}
