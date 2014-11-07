package com.github.scnonogram.rule

import com.github.scnonogram.Grid

abstract class Rule {

  def apply( grid : Grid ) : Int

}
