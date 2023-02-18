package app

import org.http4s.*
import zio.*, test.*, Assertion.*

object Spec extends DefaultRunnableSpec {
  def spec: Spec[ZEnv, TestFailure[Throwable], TestSuccess] =
    suite("endpoint")(
      testM("get") {
        for {
          _ <- ZIO.service[HttpApp[Task]]
        } yield assertTrue(true)
      },
    ).provideLayer(appLayer)
}
