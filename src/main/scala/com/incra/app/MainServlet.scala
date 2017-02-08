package com.incra.app

import com.escalatesoft.subcut.inject.BindingModule
import com.incra.model.Activity
import com.incra.services.LeaderboardService
import com.incra.services.ChallengeService
import com.incra.services.ActivityService

/**
 * @author Jeff Risberg
 * @since late August 2014
 */
class MainServlet(implicit val bindingModule: BindingModule) extends SC45Stack {

  private def activityService = inject[ActivityService]

  private def challengeService = inject[ChallengeService]

  private def leaderboardService = inject[LeaderboardService]

  get("/") {
    contentType = "text/html"

    val data1 = List("title" -> "SC45 Example")
    val data2 = data1 ++ List("city" -> "Palo Alto", "state" -> "California", "population" -> 66364)

    ssp("/index", data2.toSeq: _*)
  }

  get("/activity") {
    contentType = "text/html"

    var activities = activityService.getEntityList()

    val data1 = List("title" -> "SC45 Activities")
    val data2 = data1 ++ List("name" -> "George Washington", "activities" -> activities)

    ssp("/activity/index", data2.toSeq: _*)
  }

  get("/activity/edit/:id") {
    contentType = "text/html"

    val activityOpt = activityService.findById(params("id").toLong)

    if (activityOpt.isDefined) {
      val activity = activityOpt.get

      val data1 = List("title" -> "SC55 Activity")
      val data2 = data1 ++ List("activity" -> activity)

      ssp("/activity/edit", data2.toSeq: _*)
    }
    else {
      redirect("/activity")
    }
  }

  get("/activity/create") {
    val activity = new Activity(None, "", "", "miles")

    val data1 = List("title" -> "SC55 Activity")
    val data2 = data1 ++ List("activity" -> activity)

    ssp("/activity/edit", data2.toSeq: _*)
  }

  get("/activity/save") {
    contentType = "text/html"

    if (params("id") != "") {
      val id = params("id").toLong
      val activityOpt = activityService.findById(id)

      if (activityOpt.isDefined) {
        val name = params("name")
        val description = params("description")
        val uom = params("uom")

        val activity = new Activity(Some(id), name, description, uom)

        activityService.update(activity.id.get, activity)
      }
    }
    else {
      val name = params("name")
      val description = params("description")
      val uom = params("uom")

      val activity = new Activity(None, name, description, uom)

      activityService.insert(activity)
    }
    redirect("/activity")
  }

  get("/challenge") {
    contentType = "text/html"

    var challenges = challengeService.getEntityList()

    val data1 = List("title" -> "SC45 Challenges")
    val data2 = data1 ++ List("name" -> "Brocade-San Jose", "challenges" -> challenges)

    ssp("/challenge/index", data2.toSeq: _*)
  }

  get("/leaderboard") {
    contentType = "text/html"

    var leaderboards = leaderboardService.getEntityList()

    val data1 = List("title" -> "SC45 Leaderboards")
    val data2 = data1 ++ List("name" -> "Downhill Racer Leaders", "leaderboards" -> leaderboards)

    ssp("/leaderboard/index", data2.toSeq: _*)
  }
}
