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

  def blank( pos : Int ) = {
    cells(pos).state = Blank()
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

  def solved() : Boolean = { cells.forall( (c) => { c.solved() }) }

  def solve( src : String ) = {
    for( i <- 0 until src.length ){
      src(i) match {
        case '1' => {
          cells(i).state = Filled()
        }
        case '0' => {
          cells(i).state = Blank()
        }
        case _ => {
          // throw an exception?
        }
      }
    }
  }

  def fillEnd( start : Int = 0 ) : Int = {
    cells.indexWhere(  (c) => { !c.filled() }, start) - 1
  }

  def fillStart( end : Int = 0 ) : Int = {
    cells.lastIndexWhere(  (c) => { !c.filled() }, end ) + 1
  }
}
