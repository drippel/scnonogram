package com.github.scnonogram.rule

import com.github.scnonogram.{Line, Grid}

object EdgePushout extends Rule {

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

    val left = fromLeft(line)
    val right = fromRight(line)

    left + right
  }


  def fromLeft(line : Line) : Int = {
    // start from the left
    val block = line.blocks.head

    // find pos of first fill
    val firstFill = line.firstFill()

    if( firstFill != -1 ) {

      // is the first within the block length?
      if( firstFill <= (block.len - 1) ) {

        // lets fill from firstFill to block.len - 1

        // we need to fill and anchor those cells to the blocks
        for( i <- firstFill until block.len ) {

          line.fill(i)
          block.cells += line.cells(i)
        }

        (block.len - 1) - firstFill

      }
      else {
        0
      }
    }
    else {
      0
    }

  }

  def fromRight(line : Line) : Int = {
    // start from the end
    val block = line.blocks.last

    // find pos of last fill
    val lastFill = line.lastFill()

    if( lastFill != -1 ) {

      // is the last within the block length?
      if( lastFill >= ( line.cells.length - block.len ) ) {

        val start = ( line.cells.length - block.len )


        // we need to fill and anchor those cells to the blocks
        for( i <- start to lastFill ) {
          line.fill(i)
          block.cells += line.cells(i)
        }

        lastFill - start

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
