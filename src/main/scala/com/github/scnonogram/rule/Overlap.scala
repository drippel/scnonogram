package com.github.scnonogram.rule

import com.github.scnonogram.{Block, Line, Grid}

import scala.collection.mutable.HashMap

object Overlap extends Rule {

  override def apply(grid : Grid) : Int = {

    val overlaps = grid.rows.foldLeft(0)(
      (i,r) => {
        i + apply(r)
      }
    )

    grid.cols.foldLeft(overlaps)(
      (i,c) => { i + apply(c) }
    )
  }



  def apply( line : Line ) : Int = {

    //
    val leftMap = pushLeft(line)
    val rightMap = pushRight(line)

    val overlaps = detectOverlaps( line, leftMap, rightMap)

    applyOverlaps( line, overlaps )

  }

  def pushLeft(line : Line) : Map[Block,(Int,Int)] = {

    val left = HashMap[Block,(Int,Int)]()

    var pos = 0
    for( b <- line.blocks ){
      val end = pos + b.len - 1
      left += ( b -> (pos, end ) )
      pos = ( end + 2 )
    }

    left.toMap

  }

  def pushRight(line : Line) : Map[Block,(Int,Int)] = {

    val right = HashMap[Block,(Int,Int)]()

    var pos = line.cells.length - 1

    for( b <- line.blocks.reverse ) {
      val end = pos - ( b.len - 1 )
      right += ( b -> (end,pos))
      pos = end - 2
    }

    right.toMap

  }

  def detectOverlaps( line : Line, lefts : Map[Block, (Int, Int)], rights : Map[Block, (Int, Int)]) : Map[Block,(Int,Int)] = {

    val overlaps = HashMap[Block,(Int,Int)]()

    for( b <- line.blocks ){
      val left = lefts(b)
      val right = rights(b)
      if( left._2 >= right._1 ){
        // overlap
        Console.println( "overlap:" + b.len +" "+ right._1 + " "+ left._2 )
        overlaps += ( b -> (right._1,left._2))
      }
    }

    overlaps.toMap
  }

  def applyOverlaps(line : Line, overlaps : Map[Block,(Int,Int)]) : Int = {

    var results = 0

    //
    for( overlap <- overlaps ){

      val b = overlap._1
      val coords = overlap._2

      // TODO: move some of this logic up to parent
      for( i <- coords._1 to coords._2 ){
        // fill the line from start to end
        if( i == 25 ){
          Console.println(b.len)
        }
        line.fill(i)
        // anchor the block to the overlap region
        b.cells += line.cells(i)
      }

      results += ( coords._2 - coords._1 ) + 1

    }

   results

  }
}
