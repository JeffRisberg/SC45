package com.incra.services

import java.sql.Date

import com.escalatesoft.subcut.inject.BindingModule
import com.escalatesoft.subcut.inject.Injectable
import com.incra.model.{Activity, Challenge, TeamworkType}

import scala.collection.mutable.ListBuffer

/**
 * @author Jeff Risberg
 * @since 09/10/2014
 */
class ChallengeService(implicit val bindingModule: BindingModule) extends Injectable {

  var content = ListBuffer.empty[Challenge]

  content += new Challenge(Some(1), "Walk to the Moon", TeamworkType.Team, new Date(113, 5, 6), new Date(113, 5, 20), true)
  content += new Challenge(Some(2), "Fall Hiking", TeamworkType.Team, new Date(113, 3, 4), new Date(113, 3, 16), false)
  content += new Challenge(Some(3), "Holiday Ship-Shape", TeamworkType.Team, new Date(113, 11, 25), new Date(113, 12, 15), false)
  content += new Challenge(Some(4), "Work those Pounds Off", TeamworkType.Individual, new Date(113, 12, 25), new Date(114, 01, 15), false)

  /**
    */
  def getEntityList(): Seq[Challenge] = {
    content.toList
  }

  /**
   * @param id
   */
  def findById(id: Long): Option[Challenge] = {
    content.find { record => record.id == id }
  }
}