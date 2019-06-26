package com.github.chtjonas.weather

import scala.xml.{Elem, XML}

object RssParser {
  def parse(xml: String): Elem =
    XML.loadString(xml)

  def retrieveData(xml: Elem): Map[String, String] = {
    var map: Map[String, String] = Map()
    val descs = xml \\ "description"
    val table = descs(1).text
    val rows = parse(table) \\ "tr"
    rows.foreach(row => {
      val columns = row \\ "td"
      val key = columns(0).text
      val value = columns(1).text
      map += (key -> value)
    })
    return map
  }
}
