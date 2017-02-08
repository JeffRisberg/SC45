package com.incra.services

import com.escalatesoft.subcut.inject.{BindingModule, Injectable}
import com.incra.model._

import scala.collection.mutable.ListBuffer

/**
 * @author Jeff Risberg
 * @since 09/30/2015
 */
class LeaderboardService(implicit val bindingModule: BindingModule) extends Injectable {

  var content = ListBuffer.empty[Leaderboard]

  content += new Leaderboard(Some(1), "Walk to the Moon", Direction.Ascending)
  content += new Leaderboard(Some(2), "Fall Hiking", Direction.Ascending)
  content += new Leaderboard(Some(3), "Holiday Ship-Shape", Direction.Descending)
  content += new Leaderboard(Some(4), "Work those Pounds Off", Direction.Ascending)

  /**
    */
  def getEntityList(): Seq[Leaderboard] = {
    content.toList
  }

  /**
   * @param id
   */
  def findById(id: Long): Option[Leaderboard] = {
    content.find { record => record.id == id }
  }
}