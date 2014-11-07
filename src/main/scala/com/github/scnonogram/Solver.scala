package com.github.scnonogram

import com.github.scnonogram.rule.Overlap

class Solver {

}

object Solver {

  def main(args : Array[String]) {
    Console.println("solver")

    val grid = Parser.parse(Parser.example2)
    Generator.possibles(grid)

    Grid.print(grid)
    Grid.printHints(grid)
    Grid.printPossibleCounts(grid)

    Overlap.apply(grid)
    grid.reducePossibles()

    Grid.print(grid)
    Grid.printPossibleCounts(grid)
  }
}
