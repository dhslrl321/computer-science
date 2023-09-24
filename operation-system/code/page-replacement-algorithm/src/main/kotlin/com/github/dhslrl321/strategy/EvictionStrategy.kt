package com.github.dhslrl321.strategy

import com.github.dhslrl321.Page
import com.github.dhslrl321.Pages

interface EvictionStrategy {
  fun evict(pages: Pages): Page?
}
