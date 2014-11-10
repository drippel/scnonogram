package com.github.scnonogram

import com.github.scnonogram.rule._

class Solver {

}

object Solver {

  def main(args : Array[String]) {
    Console.println("solver")

    val grid = Parser.parse(Parser.example3)
    Generator.possibles(grid)

    Overlap.apply(grid)
    grid.reducePossibles()

    EdgePushout.apply(grid)
    grid.reducePossibles()

    EliminatePossibles.apply(grid)
    grid.reducePossibles()

    EdgePullout.apply( grid )
    grid.reducePossibles()
    Grid.print(grid)
    Grid.printPossibleCounts(grid)

    while( PossibleXOR.apply(grid) > 0 ) {
      grid.reducePossibles()
    }
    Grid.print(grid)
    Grid.printPossibleCounts(grid)
  }
}
