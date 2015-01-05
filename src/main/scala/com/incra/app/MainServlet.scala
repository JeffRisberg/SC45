package com.incra.app

import com.incra.services.{ActivityService, ChallengeService}

/**
 * @author Jeff Risberg
 * @since late August 2014
 */
class MainServlet extends SC45Stack {

  get("/") {
    contentType = "text/html"

    val data1 = List("title" -> "SC45 Example")
    val data2 = data1 ++ List("city" -> "Palo Alto", "state" -> "California", "population" -> 66364)

    ssp("/index", data2.toSeq: _*)
  }

  get("/activity") {
    contentType = "text/html"

    var activities = ActivityService.getEntityList()

    val data1 = List("title" -> "SC45 Activities")
    val data2 = data1 ++ List("name" -> "George Washington", "activities" -> activities)

    ssp("/activity/index", data2.toSeq: _*)
  }

  get("/challenge") {
    contentType = "text/html"

    var challenges = ChallengeService.getEntityList()

    val data1 = List("title" -> "SC45 Challenges")
    val data2 = data1 ++ List("name" -> "Brocade-San Jose", "challenges" -> challenges)

    ssp("/challenge/index", data2.toSeq: _*)
  }

}
