package com.github.scnonogram

import org.apache.commons.lang3.StringUtils

class Parser {

}

object Parser {
  val example1 =
    """
      20;
      2,1,4,4;2,1,5,3,2;1,2,9,2;1,7,3,1;6,5,1;
      6,7;5,9;10,5;5,2,3,2,2;2,1,2;
      3,2,1,2;5;2,5;1,5,3;1,3,3,3;
      3,2;4,2;1,3,3;2,2,3,3;2,2,3,3;
      4,1,1,1,1,1,3;2,1,1,1,2;3,1;1,1,3,1,1;8,2,4;
      5,4;5,1,2;6,2;14;6,1,4,3;
      4,1,9;3,3,8;3,3,3;5,1;7,1,2;
      3,5,2;3,5,2;1,4,2;2,6,3;10,3
    """
  // 4605461
  val example2 =
    """
      25;
      4,9;4,1,2,3;5,3;2,1,7;3,3,4,1;
      3,10,2;1,1,2,7,2;1,9,3;1,1,1,7,4;1,1,10,4;
      1,15,3;3,10,3;2,1,1,14,1;2,1,2,5,1,1,1;8,5,1,1;
      5,1,3;7,4;7,9,2;6,8,2;5,3,1,2;
      5,2,2;7,3,2;5,2;4,4;3,3,1;
      4,1,2,3,4;3,7,4;3,1,11;4,16;3,1,2,9;
      13,1,6;6,1,8;4,3,1,1;1,1,1,6;3,9,1;
      11,7;11,10;16,4;1,11,2,2;2,10,2,1;
      2,9,2;1,1,6,3,1;1,2,2,2;2,3,1,1,2;2,3,1,1;
      2,8,3;1,5,2;6;2;2
    """

  // 8111815
  val example3 =
    """
      25;
      3,10,6,1;
      4,3,1,1,6,2;
      3,1,6,7,2;
      2,3,1,3,3,2;
      1,7,8;
      5,1,9;
      1,1,5,8;
      3,1,10;
      2,5,3,4;
      3,5,9;
      6,4,1;
      3,5;
      3,4;
      3,3,1;
      3,3,2,3;
      1,2,7,1;
      1,4,2,2;
      2,2,2,2,3;
      1,1,2,7;
      1,1,3,3;
      2,4,4,2;
      4,6,1,2;
      2,8,3;
      2,1,6,2;
      1,6,2;
      3,3,4,2;
      3,4,1,2;
      4,1,1,3,1;
      1,1,3,2;
      1,1,2,2;
      3,1;
      2,1,3,4,3;
      5,6,1,2;
      1,5,7,3,3;
      3,3,3,3,5;
      1,1,3,3,11;
      3,3,2,1,4,5;
      1,5,2,5;
      1,1,2,3;
      1,1,2,1,1;
      2,3,1,3;
      4,1,3,5;
      10,1,5;
      3,4,1,1,1;
      8,4,3,1;
      13,4;
      13,6;
      7,1,1,1;
      10,1,5;
      6,1,5
    """

  def parse( raw : String ) : Grid = {

    val lines = StringUtils.split(raw,";").toList

    val size = lines.head.trim().toInt

    val hints = lines.tail.toList

    val grid = new Grid( size )

    parseLines( grid, hints )

    grid

  }

  def parseLines( grid : Grid, lines : List[String] ) = {

    // column hints are first
    for( i <- 0 until lines.length ) {

      if( i < grid.size ){
        val hint = parseLine( grid.cols(i), lines(i).trim )
      }
      else {
        val hint = parseLine( grid.rows(i-grid.size), lines(i).trim )
      }

    }
  }


  def parseLine( line : Line, raw : String ) : Unit = {

    val lens = StringUtils.split(raw,",")
    for( len <- lens ){
      line.blocks += len.toInt
    }

  }


}
