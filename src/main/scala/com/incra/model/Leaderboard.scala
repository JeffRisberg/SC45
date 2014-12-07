package com.incra.model

import com.incra.model.Direction.Direction

/**
 * Definition of the Leaderboard entity
 *
 * @author Jeff Risberg
 * @since 10/12/2014
 */
case class Leaderboard(id: Option[Long], name: String, direction: Direction) extends Entity[Long]
