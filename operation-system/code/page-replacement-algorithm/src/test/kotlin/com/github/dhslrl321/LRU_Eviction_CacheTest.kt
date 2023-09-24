package com.github.dhslrl321

import com.github.dhslrl321.strategy.LeastRecentlyUsedEvictionStrategy
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant

class LRU_Eviction_CacheTest {

  lateinit var sut: Cache

  @BeforeEach
  fun setUp() {
    val pages = Pages(
      4,
      mutableMapOf(
        "A" to Page("A", LATEST),
        "B" to Page("B", MIDDLE1),
        "C" to Page("C", MIDDLE2),
        "D" to Page("D", OLDEST)
      )
    )

    sut = Cache(pages)
  }

  @Test
  fun `LRU 에 의해 가장 오래된 순서대로 제거된다`() {
    sut.setStrategy(LeastRecentlyUsedEvictionStrategy())

    sut.get("A") shouldBe "A"
    sut.containsAll("A", "B", "C", "D") shouldBe true

    sut.get("X") shouldBe "X"
    sut.containsAll("A", "B", "C", "X") shouldBe true

    sut.get("Y") shouldBe "Y"
    sut.containsAll("A", "B", "Y", "X") shouldBe true

    sut.get("Z") shouldBe "Z"
    sut.containsAll("A", "Z", "Y", "X") shouldBe true
  }

  @Test
  fun `가장 오래된게 hit 한다면, 두 번째로 오래된게 제거된다`() {
    sut.setStrategy(LeastRecentlyUsedEvictionStrategy())

    sut.get("D") shouldBe "D"
    sut.containsAll("A", "B", "C", "D") shouldBe true

    sut.get("X") shouldBe "X"
    sut.containsAll("A", "B", "D", "X") shouldBe true
  }

  companion object {
    private val LATEST = Instant.ofEpochMilli(1695533799123)
    private val MIDDLE1 = Instant.ofEpochMilli(1695533799122)
    private val MIDDLE2 = Instant.ofEpochMilli(1695533799121)
    private val OLDEST = Instant.ofEpochMilli(1695533799120)
  }
}
