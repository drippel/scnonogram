package com.github.scnonogram.rule

import com.github.scnonogram.{Line, Grid}

import scala.collection.mutable.ListBuffer

object PossibleXOR extends Rule {
  override def apply(grid : Grid) : Int = {
    val count = grid.rows.foldLeft(0)(
      (i,r) => {
        i + apply(r)
      }
    )

    grid.cols.foldLeft(count)(
      (i,r) => {
        i + apply(r)
      }
    )
  }

  def apply( line : Line ) : Int = {

    if( !line.solved() ){
      var results = ListBuffer[Char]()
      for( c <- line.possibles.head ){
        results += c
      }

      for( p <- line.possibles ){

        for( i <- 0 until p.length ){

          if( results(i) == p(i) ) {
            // we're good
          }
          else {
            results(i) = '.'
          }

        }
      }

      var count = 0
      for( i <- 0 until results.length ){
        results(i) match {
          case '1' => {
            count += 1
            line.fill(i)
          }
          case '0' => {
            count += 1
            line.blank(i)
          }
          case '.' => {
            // Console.println("nope " + i)
          }

        }
      }

      count
    }
    else {
      0
    }
  }
}
