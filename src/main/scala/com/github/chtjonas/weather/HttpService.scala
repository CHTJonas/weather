package com.github.chtjonas.weather

import com.twitter.finagle.Service
import com.twitter.finagle.http
import com.twitter.util.{Await,Future}

object HttpService {
  val service = new Service[http.Request, http.Response] {
    def apply(req: http.Request): Future[http.Response] = {
      val response = http.Response(req.version, http.Status.Ok)
      Await.ready(RssClient.response).onSuccess { rssResponse: http.Response =>
        val xmlString = rssResponse.contentString
        val map = RssParser.parse(xmlString)
        val responseBody = map.toString()
        response.setContentString(responseBody)
      }
      Future.value(response)
    }
  }
}
