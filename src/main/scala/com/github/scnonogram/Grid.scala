package com.github.scnonogram

import scala.collection.mutable.ListBuffer

class Grid(val size : Int ) {

  var cells = Array.fill(size,size){ -1 }

  val rows = {
    val rs = ListBuffer[Line]()
    for( y <- 0 until size ){
      val r = new Line(this)
      for( x <- 0 until size ){
        val c = (x,y)
        r.cells += c
      }
      rs += r
    }
    rs.toList
  }

  val cols = {
    val rs = ListBuffer[Line]()
    for( x <- 0 until size ){
      val r = new Line(this)
      for( y <- 0 until size ){
        val c = (x,y)
        r.cells += c
      }
      rs += r
    }
    rs.toList
  }


  def reducePossibles() = {
    for( r <- rows ){
      r.reducePossibles()
    }
    for( r <- cols ){
      r.reducePossibles()
    }
  }
}

object Grid {

  def print( grid : Grid ) = {
    for( i <- 0 until grid.rows.length ){
      Console.print(" | ")
      for( j <- 0 until grid.rows(i).cells.length ){
        val cell = grid.rows(i).cells(j)
        val c = grid.cells(cell._1)(cell._2) match {
          case 1 => {'0'}
          case 0 => {'x'}
          case _  => {'.'}
        }
        Console.print(c)
          if( ( j + 1 ) % 5 == 0 ){
            Console.print(" | ")
          }
      }
      Console.print("\n")
      if( (i + 1) % 5 == 0 ){
        Console.print("\n")
      }
    }
  }

  def printHints( grid : Grid ) = {
    Console.println("Cols:")
    for( l <- grid.cols ){
      Console.println( l.blockLengths().mkString(","))
    }
    Console.println("Rows:")
    for( l <- grid.rows ){
      Console.println( l.blockLengths().mkString(","))
    }
  }

  def printPossibleCounts( grid : Grid ) = {
    Console.println( "possibles")
    Console.println( "rows")
    val rcounts = grid.rows.map( _.possibles.length )
    Console.println( rcounts.mkString(",") )
    Console.println( "cols")
    val ccounts = grid.cols.map( _.possibles.length )
    Console.println( ccounts.mkString(",") )

  }
}

