package com.github.chtjonas.weather

import com.twitter.app.App
import com.twitter.util.Await

object Application extends App {
  premain { println("Starting up...") }
  onExit { println("Shutting down...") }

  def main() = {
    val server = HttpService.server
    closeOnExit(server)
    Await.ready(server)
  }
}
