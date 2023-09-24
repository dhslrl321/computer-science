package com.github.dhslrl321.strategy

import com.github.dhslrl321.Page
import com.github.dhslrl321.Pages

class LeastRecentlyUsedEvictionStrategy : EvictionStrategy {
  override fun evict(pages: Pages): Page? {

    val sorted = pages.pageMap
      .toList()
      .sortedBy { it.second.age }

    return pages.pageMap
      .remove(sorted[0].first)
  }
}
