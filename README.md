# Weather
A very simple API for obtaining data from the Digital Technology Group's weather station at the University of Cambridge Computer Lab. Please see https://www.cl.cam.ac.uk/research/dtg/weather/ for more details.

## Compiling
You will need to have Java 8 and the [Scala Build Tool](https://www.scala-sbt.org) installed. Then run the following in your terminal:

```bash
sbt package
sbt "run com.github.chtjonas.weather.Application -port 8080"
```

This will start a development HTTP server bound to the default port of 8080.

## Motivation
This project is primarily intended as a learning exercise in concurrent programming and RPC engineering in Scala. Feel free to repurpose to your own ends!
