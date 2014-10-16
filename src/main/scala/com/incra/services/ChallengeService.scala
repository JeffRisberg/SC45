package com.incra.services

import java.sql.Date

import com.incra.model.{Challenge, TeamworkType}

import scala.collection.mutable.ListBuffer

/**
 * @author Jeff Risberg
 * @since 09/10/2014
 */
object ChallengeService {
  def getEntityList(): List[Challenge] = {
    var challenges = ListBuffer.empty[Challenge]

    challenges += new Challenge(Some(1), "Walk to the Moon", TeamworkType.Team, new Date(213, 5, 6), new Date(213, 5, 20), true)
    challenges += new Challenge(Some(2), "Fall Hiking", TeamworkType.Team, new Date(213, 3, 4), new Date(213, 3, 16), false)
    challenges += new Challenge(Some(3), "Holiday Ship-Shape", TeamworkType.Team, new Date(213, 11, 25), new Date(213, 12, 15), false)

    challenges.toList
  }
}