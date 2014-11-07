package com.github.scnonogram

class Solver {

}

object Solver {

  def main(args : Array[String]) {
    Console.println("solver")

    val grid = Parser.parse(Parser.example2)

    Grid.print(grid)

    Generator.possibles(grid)

    Grid.printHints(grid)
  }
}
