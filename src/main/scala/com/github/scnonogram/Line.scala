package com.github.scnonogram

import scala.collection.mutable.ListBuffer

class Line {

  val cells = ListBuffer[Cell]()
  val subLines = ListBuffer[Cell]()
  val blocks = ListBuffer[Block]()
  var possibles = ListBuffer[String]()

  var blockLens = ListBuffer[Int]()
  var blockSums = -1


  def blockLengths() : ListBuffer[Int] = {
    if( blockLens.isEmpty ){
      blockLens = blocks.map( _.len )
    }
    blockLens
  }
  def blockSum() : Int = {
    if( blockSums == -1 ) {
      val lens = blockLengths()
      blockSums = lens.foldLeft(0)((i, l) => {
        i + l
      })
    }
    blockSums
  }


  def unsolved() : List[Line] = {
    List(this)
  }

  def fill( pos : Int ) = {
    cells(pos).state = Filled()
  }

  def reducePossibles() = {

    for( i <- 0 until cells.length ) {
      val c = cells(i)
      c.state match {
        case Filled() => {
          possibles = possibles.filter( (s) => { s(i) == '1' })
        }
        case Blank() => {
          possibles = possibles.filter( (s) => { s(i) == '0' })
        }
        case _ => {}
      }
    }

  }

  def firstFill() : Int = {
    cells.indexWhere( (c) => { c.filled() })
  }

  def lastFill() : Int = {
    cells.lastIndexWhere( (c) => { c.filled() })
  }
}
