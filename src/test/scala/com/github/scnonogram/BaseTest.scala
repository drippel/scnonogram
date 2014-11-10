package com.github.scnonogram

import org.junit.Assert._

class BaseTest {

  def emptyLine( size : Int = 25 ) = {

    val line = new Line()

    for( i <- 0 until size ){
      line.cells += new Cell()
    }

    line
  }

  def addBlock( line : Line, size : Int ) = {
    line.blocks += new Block(size)
  }

  def assertUnsolved( line : Line, ps : List[Int] ) = {
    ps.foreach( (p) => { assertTrue( line.cells(p).unsolved() )})
  }

  def assertFilled( line : Line, ps : List[Int] ) = {
    ps.foreach( (p) => { assertTrue( line.cells(p).filled() )})
  }

  def assertBlank( line : Line, ps : List[Int] ) = {
    ps.foreach( (p) => { assertTrue( line.cells(p).blank() )})
  }
}
