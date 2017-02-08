package com.incra.model

import java.sql.Date

import com.incra.model.TeamworkType.TeamworkType

/**
 * Definition of the Challenge entity
 *
 * @author Jeff Risberg
 * @since 06/11/2014 (revised to use non-case classes, October 2015)
 */
class Challenge(var id: Option[Long],
                var name: String,
                var teamworkType: TeamworkType,
                var startDate: Date,
                var endDate: Date,
                var active: Boolean) extends Entity[Long]
