package com.github.scnonogram.rule

import com.github.scnonogram.{Line, Grid}

object EdgePullout extends Rule {
  override def apply(grid : Grid) : Int = {

    val count = grid.rows.foldLeft(0)(
      (i,r) => {
        i + apply(r)
      }
    )

    grid.cols.foldLeft(count)(
      (i,r) => {
        i + apply(r)
      }
    )
  }


  def apply(line : Line) : Int = {

    if( !line.solved() ){
      applyLeft( line ) + applyRight(line)
    }
    else {
      0
    }
  }

  def applyLeft(line : Line) : Int = {

    val left = line.firstFill()
    val block = line.blocks.head

    // is the left within the block length
    if( left <= ( block.len - 1 ) ){

      // get the end of the run
      val end = line.fillEnd(left)

      // if the end is after block length
      if( end >= (block.len - 1) ){

        for( i <- 0 to (end - block.len ) ){
          line.blank(i)
        }

        //
        (end - block.len)
      }
      else {
        0
      }
    }
    else {
      0
    }
  }

  def applyRight(line : Line) : Int = {

    val right = line.lastFill()
    val block = line.blocks.last

    // is the left within the block length
    if( right >= ( line.cells.length - block.len ) ){

      // get the end of the run
      val end = line.fillStart(right)

      // if the end is after block length
      if( end <= ( line.cells.length - block.len ) ){

        // blank out
        val diff = ((line.cells.length - end) - block.len)

        for( i <- 1 to diff ){
          line.blank( (line.cells.length - i))
        }

        0
      }
      else {
        0
      }
    }
    else {
      0
    }


  }
}
