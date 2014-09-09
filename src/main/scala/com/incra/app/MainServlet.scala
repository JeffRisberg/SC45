package com.incra.app

class MainServlet extends SC45Stack {

  get("/") {
    contentType = "text/html"

    val data1 = List("title" -> "SC45 Example")
    val data2 = data1 ++ List("city" -> "Palo Alto", "state" -> "California", "population" -> 66363)

    ssp("/index", data2.toSeq: _*)
  }

  get("/activity") {
    contentType = "text/html"

    var activities = List("Hiking", "Walking", "Pilates", "Biking", "Spins", "Exercise")

    val data1 = List("title" -> "SC45 Activities")
    val data2 = data1 ++ List("name" -> "George Washington", "activities" -> activities)

    ssp("/activity/index", data2.toSeq: _*)
  }

  get("/challenge") {
    contentType = "text/html"

    var challenges = List("Fall Biking", "Walk to the Moon", "Holiday Ship-Shape", "2014 Olypmics")

    val data1 = List("title" -> "SC45 Challenges")
    val data2 = data1 ++ List("name" -> "Brocade-San Jose", "challenges" -> challenges)

    ssp("/challenge/index", data2.toSeq: _*)
  }

}
