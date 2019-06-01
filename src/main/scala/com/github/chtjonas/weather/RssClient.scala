package com.github.chtjonas.weather

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http
import com.twitter.util.Future

object RssClient {
  private val domain = "www.cl.cam.ac.uk"
  private val url = "/research/dtg/weather/rss.xml"
  private val client: Service[http.Request, http.Response] = Http.client
    .withTransport.tls(domain)
    .newService(domain + ":443")
  private val request = http.Request(http.Method.Get, url)
  request.host = domain

  def response: Future[http.Response] =
    client(request)
}
