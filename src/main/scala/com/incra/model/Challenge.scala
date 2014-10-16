package com.incra.model

import java.sql.Date

/**
 * Definition of the Challenge entity
 *
 * @author Jeff Risberg
 * @since 06/11/2014
 */
case class Challenge(id: Option[Long], name: String, teamworkType: TeamworkType,
                     startDate: Date, endDate: Date, active: Boolean) extends Entity[Long]
