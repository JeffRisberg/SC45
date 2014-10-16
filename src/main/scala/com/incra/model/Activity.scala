package com.incra.model

/**
 * Definition of the Activity entity
 *
 * @author Jeffrey Risberg
 * @since 06/10/2014
 */
case class Activity(id: Option[Long], name: String, description: String, uom: String) extends Entity[Long]
