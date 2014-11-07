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
    val blocks = stringToBlocks(s)
    val blockLengths = blocks.map( (b) => { b.len } )
    for( row <- grid.rows ){
      addPossible( row, s, bitCount, blocks, blockLengths )
    }
    for( col <- grid.cols ){
      addPossible( col, s, bitCount, blocks, blockLengths )
    }
  }

  def addPossible( line : Line, s : String, bitCount : Int, blocks : ListBuffer[Block], blockLengths : ListBuffer[Int] ) : Unit = {
    if( line.blockSum() == bitCount
      && line.blocks.length == blocks.length
      && sameOrder( line, blockLengths )){
      line.possibles += s
    }
  }


  def stringToBlocks( src : String ) : ListBuffer[Block] = {

    val blocks = ListBuffer[Block]()

    var currBlock : Block = null
    for( i <- 0 until src.length ){

      src(i) match {
        case '1' => {
          if( currBlock == null ){
            currBlock = new Block(1)
            blocks += currBlock
          }
          else {
            currBlock.len += 1
          }
        }
        case '0' => {
          if( currBlock != null ){
            currBlock = null
          }
        }
      }
    }

    blocks
  }

  def sameOrder( line : Line, blockLengths : ListBuffer[Int] ) : Boolean = {
    val zipped = line.blockLengths().zip(blockLengths)
    zipped.forall( (t) => { t._1 == t._2 } )
  }

}
