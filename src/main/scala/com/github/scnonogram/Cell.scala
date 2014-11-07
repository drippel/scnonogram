package com.github.scnonogram

class Cell( var state : CellState = Unsolved() ) {

  def solved() : Boolean = { filled() || blank() }
  def filled() : Boolean = { state == Filled }
  def blank() : Boolean = { state == Blank }

  override def toString() : String = {
    state match {
      case Filled() => {"0"}
      case Blank() => {"X"}
      case _ => {"."}
    }
  }
}
