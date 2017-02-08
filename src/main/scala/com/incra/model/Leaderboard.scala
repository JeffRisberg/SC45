package com.incra.model

import com.incra.model.Direction.Direction

/**
 * Definition of the Leaderboard entity
 *
 * @author Jeff Risberg
 * @since 10/12/2014 (revised to use non-case classes, October 2015)
 */
class Leaderboard(var id: Option[Long],
                  var name: String,
                  var direction: Direction) extends Entity[Long]
