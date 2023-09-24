package com.github.dhslrl321

interface EvictionStrategy {
  fun evict(pages: Pages): Page?
}
