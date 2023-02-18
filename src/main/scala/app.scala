package app

import io.circe.Encoder
import org.http4s.*
import org.http4s.circe.jsonEncoderOf
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import zio.*
import zio.interop.catz.*

val routes: UIO[HttpApp[Task]] =
  val dsl: Http4sDsl[Task] = Http4sDsl[Task]
  import dsl.*

  given EntityEncoder[Task, List[String]] = jsonEncoderOf

  ZIO.succeed(
    Router[Task](
      "/" -> HttpRoutes.of {
        case GET -> Root => Ok(List("1"))
      },
    ).orNotFound
  )

val appLayer: ULayer[Has[HttpApp[Task[*]]]] = ZLayer.fromEffect(routes)
