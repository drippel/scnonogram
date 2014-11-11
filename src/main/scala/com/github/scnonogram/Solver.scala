package com.github.scnonogram

import com.github.scnonogram.rule._

class Solver {

}

object Solver {

  def main(args : Array[String]) {
    Console.println("solver")

    Console.println("generate...")
    val start = System.currentTimeMillis()

    val grid = Parser.parse(Parser.example2)
    Generator.possibles(grid)

    val gen = System.currentTimeMillis()

    var iters=0

    Console.println("solve...")
    // PossibleXOR.apply(grid)
    while( PossibleXOR.apply(grid) > 0 ) {
    grid.reducePossibles()
      iters += 1
      Console.println(iters)
      Grid.print(grid)
     }

    val solve = System.currentTimeMillis()

    Grid.print(grid)

    Console.println( "Gen:"+ (gen-start))
    Console.println( "Solve:"+ (solve-gen))
  }
}
