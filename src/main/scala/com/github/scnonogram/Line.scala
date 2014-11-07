package com.github.scnonogram

import scala.collection.mutable.ListBuffer

class Line {

  val cells = ListBuffer[Cell]()
  val subLines = ListBuffer[Cell]()
  val blocks = ListBuffer[Block]()
  val possibles = ListBuffer[String]()


  def blockLengths() : ListBuffer[Int] = { blocks.map( _.len ) }
  def blockSum() : ListBuffer[Int] = { blocks.map( _.len ) }

}
