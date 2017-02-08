package com.incra.model

/**
 * Definition of the Activity entity
 *
 * @author Jeffrey Risberg
 * @since 06/10/2014 (revised to use non-case classes, October 2015)
 */
class Activity(var id: Option[Long],
               var name: String,
               var description: String,
               var uom: String) extends Entity[Long] {

  def setName(name: String): Unit = {
    this.name = name
  }

  def setDescription(description: String): Unit = {
    this.description = description
  }
}
