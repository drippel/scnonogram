package com.github.scnonogram

import scala.collection.mutable.ListBuffer

class Grid(val size : Int ) {

  val cells = Array.fill(size,size){ new Cell() }

  val rows = {
    val rs = ListBuffer[Line]()
    for( y <- 0 until size ){
      val r = new Line()
      for( x <- 0 until size ){
        r.cells += cells(x)(y)
      }
      rs += r
    }
    rs.toList
  }

  val cols = {
    val rs = ListBuffer[Line]()
    for( x <- 0 until size ){
      val r = new Line()
      for( y <- 0 until size ){
        r.cells += cells(x)(y)
      }
      rs += r
    }
    rs.toList
  }
}

object Grid {

  def print( grid : Grid ) = {
    for( i <- 0 until grid.rows.length ){
      Console.print(" | ")
      for( j <- 0 until grid.rows(i).cells.length ){
        Console.print( grid.rows(i).cells(j) )
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
}

