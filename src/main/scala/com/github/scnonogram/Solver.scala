package com.github.scnonogram

import com.github.scnonogram.rule._

class Solver {

}

object Solver {

  def main(args : Array[String]) {
    Console.println("solver")

    val start = System.currentTimeMillis()

    val grid = Parser.parse(Parser.example3)
    Generator.possibles(grid)

    val gen = System.currentTimeMillis()
    // Overlap.apply(grid)
    // grid.reducePossibles()

    // EdgePushout.apply(grid)
    // grid.reducePossibles()

    // EliminatePossibles.apply(grid)
    // grid.reducePossibles()

    // EdgePullout.apply( grid )
    // grid.reducePossibles()
    // Grid.print(grid)
    // Grid.printPossibleCounts(grid)

    var iters=0

    while( PossibleXOR.apply(grid) > 0 ) {
      grid.reducePossibles()
      iters += 1
      // Console.println(iters)
    }

    val solve = System.currentTimeMillis()

    Grid.print(grid)

    Console.println( "Gen:"+ (gen-start))
    Console.println( "Solve:"+ (solve-gen))
  }
}
