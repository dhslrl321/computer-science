package com.github.dhslrl321

class LeastRecentlyUsedEvictionStrategy : EvictionStrategy {
  override fun evict(pages: Pages): Page? {

    val sorted = pages.pageMap
      .toList()
      .sortedBy { it.second.age }

    return pages.pageMap
      .remove(sorted[0].first)
  }
}
