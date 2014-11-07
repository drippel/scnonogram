package com.github.scnonogram

import com.github.scnonogram.rule.Overlap
import org.junit.Test
import org.junit.Assert._

class OverlapTest extends BaseTest {

  @Test
  def test_overlap_1() = {

    val line = emptyLine(25)

    addBlock( line, 16)

    val result = Overlap.apply(line)

    assertTrue( result == 7 )
    assertUnsolved( line, List(0,1,2,3,4,5,6,7,8))
    assertUnsolved( line, List(16,17,18,19,20,21,22,23,24))
    assertFilled( line, List( 9,10,11,12,13,14,15))
  }

  @Test
  def test_overlap_2() = {

    val line = emptyLine(25)

    addBlock( line, 16)
    addBlock( line, 4)

    val result = Overlap.apply(line)

    assertTrue( result == 12 )
    assertUnsolved( line, List(0,1,2,3))
    assertUnsolved( line, List(16,17,18,19,20,21,22,23,24))
    assertFilled( line, List( 4,5,6,7,8,9,10,11,12,13,14,15))
  }

  @Test
  def test_overlap_3() = {

    val line = emptyLine(25)

    addBlock( line, 11)
    addBlock( line, 10)

    val result = Overlap.apply(line)

    assertTrue( result == 15 )
    assertUnsolved( line, List(0,1,2))
    assertUnsolved( line, List(11,12,13,14))
    assertUnsolved( line, List(22,23,24))
    assertFilled( line, List( 3,4,5,6,7,8,9,10 ))
    assertFilled( line, List( 15,16,17,18,19,20,21 ))

  }
}
