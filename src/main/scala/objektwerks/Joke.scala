package objektwerks

import scala.io.{Codec, Source}
import scala.util.Using

def getJoke(context: Context): String =
  Using( Source.fromURL(context.jokeUrl, Codec.UTF8.name) ) { 
    source => s"* ${parseJson( source.mkString )}"
  }.getOrElse(context.jokeError)

private def parseJson(json: String): String = ujson.read(json)("value").str