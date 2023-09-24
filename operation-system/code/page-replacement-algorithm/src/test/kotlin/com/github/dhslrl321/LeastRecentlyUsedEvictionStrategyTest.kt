package com.github.dhslrl321

import com.github.dhslrl321.strategy.LeastRecentlyUsedEvictionStrategy
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import java.time.Instant

class LeastRecentlyUsedEvictionStrategyTest {

  lateinit var sut: LeastRecentlyUsedEvictionStrategy

  @Test
  fun name() {

    val pages = Pages(
      4,
      mutableMapOf(
        "A" to Page("A", LATEST),
        "B" to Page("B", MIDDLE1),
        "C" to Page("C", MIDDLE2),
        "D" to Page("D", OLDEST)
      )
    )

    sut = LeastRecentlyUsedEvictionStrategy()

    val actual = sut.evict(pages)

    actual shouldNotBe null
    actual!!.data shouldBe "D"
    pages.size() shouldBe 3
  }

  companion object {
    private val LATEST = Instant.ofEpochMilli(1695533799123)
    private val MIDDLE1 = Instant.ofEpochMilli(1695533799122)
    private val MIDDLE2 = Instant.ofEpochMilli(1695533799121)
    private val OLDEST = Instant.ofEpochMilli(1695533799120)
  }
}
