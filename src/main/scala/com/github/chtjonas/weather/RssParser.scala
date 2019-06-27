package com.github.chtjonas.weather

import scala.xml.{Elem, XML}

object RssParser {
  def toXml(str: String): Elem =
    XML.loadString(str)

  def parse(responseText: String): Map[String, String] = {
    val str = responseText.replace("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>", "")
    val xml = toXml(str)
    parseData(xml)
  }

  def parseData(xml: Elem): Map[String, String] = {
    var map: Map[String, String] = Map()
    val descs = xml \\ "description"
    val table = descs(1).text
    val rows = toXml(table) \\ "tr"
    rows.foreach(row => {
      val columns = row \\ "td"
      val key = columns(0).text
      val value = removeUnits(columns(1).text)
      map += (key -> value)
    })
    return map
  }

  def removeUnits(value: String): String = {
    val units = List("deg", "mm/1000", "knots", "%", "C", "mBar", "secs")
    value.split(" ").map { word => if (units.contains(word)) "" else word }.mkString
  }
}
