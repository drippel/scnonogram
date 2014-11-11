package com.github.scnonogram

import org.apache.commons.lang3.StringUtils

import scala.collection.mutable.ListBuffer

class Generator {

}

object Generator {

  def possibles( grid : Grid ) = {
    for( i <- 0 until scala.math.pow(2, grid.size).toInt ) {
      val s = StringUtils.leftPad(i.toBinaryString, 25, '0')
      addPossible(grid, s)
    }
  }

  def count( s : String ) : Int = {
    s.foldLeft(0)(
      (i,c) => {
        c match {
          case '1' => { i + 1 }
          case _ => { i }
        }
      }
    )
  }

  def addPossible( grid : Grid, s : String ) : Unit  ={
    val bitCount = count(s)
    val blockLengths = stringToBlocks(s)

        for( row <- grid.rows ) {
          addPossible(row, s, bitCount, blockLengths)
        }

        for( col <- grid.cols ) {
          addPossible(col, s, bitCount, blockLengths)
        }
  }

  def addPossible( line : Line, s : String, bitCount : Int, blockLengths : ListBuffer[Int] ) : Unit = {
    // same number of filled cells
    // same number of blocks
    // and same order of blocks
    if( line.blockSum() == bitCount
      && line.blocks.length == blockLengths.length
      && sameOrder( line, blockLengths )){
      line.possibles += s
    }
  }


  def stringToBlocks( src : String ) : ListBuffer[Int] = {

    val blocks = ListBuffer[Int]()

    var currBlock : Int = -1
    for( i <- 0 until src.length ){

      src(i) match {
        case '1' => {
          if( currBlock == -1 ){
            // start a block
            currBlock = 1
          }
          else {
            currBlock += 1
          }
        }
        case '0' => {
          if( currBlock != -1 ){
            blocks += currBlock
            currBlock = -1
          }
        }
      }
    }

    if( currBlock != -1 ){
      blocks += currBlock
    }

    blocks
  }

  def sameOrder( line : Line, blockLengths : ListBuffer[Int] ) : Boolean = {
    val zipped = line.blockLengths().zip(blockLengths)
    zipped.forall( (t) => { t._1 == t._2 } )
  }

}
