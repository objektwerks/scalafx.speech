package objektwerks

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class StoreTest extends AnyFunSuite with Matchers:
  test("store"):
    val mp3 = "test.mp3"
    val path = os.pwd / "src" / "test" / "resources" / mp3
    val bytes = os.read(path).getBytes()

    val store = Store()
    
    val uri = store.writeFile(bytes, mp3)
    println(s"*** File URI: $uri")

    store.readFile(mp3).nonEmpty shouldBe true

    store.listFiles.length shouldBe 1