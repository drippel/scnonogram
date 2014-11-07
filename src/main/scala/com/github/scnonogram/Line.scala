package com.github.scnonogram

import scala.collection.mutable.ListBuffer

class Line {

  val cells = ListBuffer[Cell]()
  val subLines = ListBuffer[Cell]()
  val blocks = ListBuffer[Block]()
  val possibles = ListBuffer[String]()

  var blockLens = ListBuffer[Int]()
  var blockSums = -1


  def blockLengths() : ListBuffer[Int] = {
    if( blockLens.isEmpty ){
      blockLens = blocks.map( _.len )
    }
    blockLens
  }
  def blockSum() : Int = {
    if( blockSums == -1 ) {
      val lens = blockLengths()
      blockSums = lens.foldLeft(0)((i, l) => {
        i + l
      })
    }
    blockSums
  }


  def unsolved() : List[Line] = {
    List(this)
  }

}
