package com.github.scnonogram

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


  }
}
