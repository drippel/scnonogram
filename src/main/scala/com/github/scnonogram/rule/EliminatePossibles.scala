package com.github.scnonogram.rule

import com.github.scnonogram.{Line, Grid}

object EliminatePossibles extends Rule {

  override def apply(grid : Grid) : Int = {

    val count = grid.rows.foldLeft(0)(
      (i,l) => { i + apply(l) }
    )

    grid.rows.foldLeft(count)(
      (i,l) => { i + apply(l) }
    )
  }

  def apply( line : Line ) : Int = {

    if( !line.solved() ){

      if( line.possibles.length == 1 ){
        line.solve(line.possibles.head)
        1
      }
      else {
        0
      }
    }
    else {
      0
    }

  }
}
