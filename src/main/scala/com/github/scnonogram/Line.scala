package com.github.scnonogram

import scala.collection.mutable.ListBuffer

class Line( grid : Grid ) {

  val cells = ListBuffer[(Int,Int)]()

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


  def fill( pos : Int ) = {
    val c = cells(pos)
    grid.cells(c._1)(c._2) = 1
  }

  def blank( pos : Int ) = {
    val c = cells(pos)
    grid.cells(c._1)(c._2) = 0
  }

  def reducePossibles() = {

    for( i <- 0 until cells.length ) {
      val cell = cells(i)
      val c = grid.cells(cell._1)(cell._2)
      c match {
        case 1 => {
          possibles = possibles.filter( (s) => { s(i) == '1' })
        }
        case 0 => {
          possibles = possibles.filter( (s) => { s(i) == '0' })
        }
        case _ => {}
      }
    }

  }

  def solved() : Boolean = { cells.forall( (c) => {
    grid.cells(c._1)(c._2) == 1 || grid.cells(c._1)(c._2) == 0 } )
  }

  def solve( src : String ) = {
    for( i <- 0 until src.length ){
      val cell = cells(i)
      src(i) match {
        case '1' => {
          grid.cells(cell._1)(cell._2) = 1
        }
        case '0' => {
          grid.cells(cell._1)(cell._2) = 0
        }
        case _ => {
          // throw an exception?
        }
      }
    }
  }

}
