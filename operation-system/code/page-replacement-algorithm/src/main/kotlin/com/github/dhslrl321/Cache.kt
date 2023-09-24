package com.github.dhslrl321

import java.time.Instant

data class Cache(
  val pages: Pages,
) {

  private lateinit var evictionStrategy: EvictionStrategy

  fun setStrategy(strategy: EvictionStrategy) {
    this.evictionStrategy = strategy
  }

  fun get(data: String): String {
    val page = pages.get(data) ?: run {
      return replacement(data).data
    }
    return page.data()
  }

  fun containsAll(vararg s: String): Boolean =
    s.all { pages.pageMap.contains(it) }

  private fun replacement(data: String): Page {
    evictionStrategy.evict(pages)
    val newPage = newPage(data)
    pages.put(newPage)
    return newPage
  }

  private fun newPage(data: String) =
    Page(data, Instant.now())
}
