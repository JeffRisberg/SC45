package com.incra.services

import com.incra.model.Activity

import scala.collection.mutable.ListBuffer

/**
 * @author Jeff Risberg
 * @since 09/10/2014
 */
object ActivityService {

  def getEntityList(): List[Activity] = {
    var activities = ListBuffer.empty[Activity]

    activities += new Activity(Some(1), "Hiking", "head up the mountain", "miles")
    activities += new Activity(Some(2), "Walking", "enjoy the view", "steps")
    activities += new Activity(Some(3), "Pilates", "take care of yourself", "times")
    activities += new Activity(Some(4), "Biking", "spin those wheels", "miles")
    activities += new Activity(Some(5), "Exercise", "do anything", "minutes")
    activities.toList
  }
}
