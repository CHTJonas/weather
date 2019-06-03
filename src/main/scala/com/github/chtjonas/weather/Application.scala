package com.github.chtjonas.weather

import java.net.InetSocketAddress
import com.twitter.app.{App, Flag}
import com.twitter.finagle.Http
import com.twitter.util.Await

object Application extends App {
  val port = flag[Int]("port", 8080, "port this server should bind to")

  premain {
    println("Starting server. Listening on port " + port().toString)
  }

  onExit {
    println("Received termination signal. Shutting down...")
  }

  def main() = {
    val socket = new InetSocketAddress(port())
    val server = Http.serve(socket, HttpService.service)
    closeOnExit(server)
    Await.ready(server)
  }
}
