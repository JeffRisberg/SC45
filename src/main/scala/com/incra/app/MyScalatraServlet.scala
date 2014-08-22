package com.incra.app

class MyScalatraServlet extends Sct01Stack {

  get("/") {
    contentType = "text/html"

    val data2 = List("title" -> "Title", "headline" -> "Headline")
    val data3 = data2 ++ List("foo" -> 3, "bar" -> 3.1415)

    ssp("/index", data3.toSeq: _*)
  }
  get("/activity") {
    contentType = "text/html"

    var activities = List("Hiking", "Walking", "Pilates", "Biking", "Spins", "Exercise")

    val data2 = List("title" -> "Title", "headline" -> "Headline")
    val data3 = data2 ++ List("name" -> "George Washington", "activities" -> activities)

    ssp("/activity", data3.toSeq: _*)
  }

}
