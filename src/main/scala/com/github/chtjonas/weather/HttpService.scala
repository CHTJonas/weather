package com.github.chtjonas.weather

import com.twitter.finagle.Service
import com.twitter.finagle.http
import com.twitter.util.Future

object HttpService {
  val service = new Service[http.Request, http.Response] {
    def apply(req: http.Request): Future[http.Response] =
      RssClient.response
  }
}
