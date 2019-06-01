package com.github.chtjonas.weather

import com.twitter.app.App
import com.twitter.finagle.Http
import com.twitter.util.Await

object Application extends App {
  premain { println("Starting up...") }
  onExit { println("Shutting down...") }

  def main() = {
    val server = Http.serve(":8080", HttpService.service)
    closeOnExit(server)
    Await.ready(server)
  }
}
