package com.github.scnonogram

abstract class CellState( val value : Int ) {  }
case class Unsolved() extends CellState(-1)
case class Filled() extends CellState(1)
case class Blank() extends CellState(0)
