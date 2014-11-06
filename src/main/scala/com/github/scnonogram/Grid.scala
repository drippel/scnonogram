package com.github.scnonogram

import scala.collection.mutable.ListBuffer

class Grid(val size : Int ) {

  val cells = Array.fill(size,size){-1}

  val rows = {
    val rs = ListBuffer[List[Int]]()
    for( y <- 0 until size ){
      val r = ListBuffer[Int]()
      for( x <- 0 until size ){
        r += cells(x)(y)
      }
      rs += r.toList
    }
    rs.toList
  }

  val cols = {
    val rs = ListBuffer[List[Int]]()
    for( x <- 0 until size ){
      val r = ListBuffer[Int]()
      for( y <- 0 until size ){
        r += cells(x)(y)
      }
      rs += r.toList
    }
    rs.toList
  }
}

object Grid {

  def print( grid : Grid ) = {

    for( r <- grid.rows ){
      for( i <- r ){
        Console.print(i)
      }
      Console.print("\n")
    }

  }
}
