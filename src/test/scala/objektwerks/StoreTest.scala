package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class StoreTest extends AnyFunSuite with Matchers:
  test("store"):
    val path = os.pwd / "src" / "test" / "resources" / "test.mp3"
    val bytes = os.read(path).getBytes()

    val store = Store()
    val uri = store.writeFile(bytes, "test.mp3")
    println(s"*** File URI: $uri")