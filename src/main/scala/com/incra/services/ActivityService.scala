package com.incra.services

import com.escalatesoft.subcut.inject.{Injectable, BindingModule}
import com.incra.model.Activity

import scala.collection.mutable.ListBuffer
import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration

/**
 * @author Jeff Risberg
 * @since 09/10/2014
 */
class ActivityService(implicit val bindingModule: BindingModule) extends Injectable {

  var content = ListBuffer.empty[Activity]

  content += new Activity(Some(1), "Hiking", "head up the mountain", "miles")
  content += new Activity(Some(2), "Walking", "enjoy the view", "steps")
  content += new Activity(Some(3), "Pilates", "take care of yourself", "times")
  content += new Activity(Some(4), "Biking", "spin those wheels", "miles")
  content += new Activity(Some(5), "Zumba", "dance, dance, dance!", "minutes")
  content += new Activity(Some(6), "Martial Arts", "release your inner ninja", "minutes")
  content += new Activity(Some(7), "Exercise", "do anything", "minutes")

  /**
   */
  def getEntityList(): Seq[Activity] = {
    content.toList
  }

  /**
   * @param id
   */
  def findById(id: Long): Option[Activity] = {
    content.find { record => record.id == id }
  }

  /**
   * @param activity
   * @return
   */
  def insert(activity: Activity): Int = {
    content += activity
  }

  /**
   * @param id
   * @param activity
   */
  def update(id: Long, activity: Activity): Int = {
    var existingActivity = findById(id)

    if (existingActivity.isDefined) {
      existingActivity.get.name = activity.name
      existingActivity.get.description = activity.description
    }
  }

}
