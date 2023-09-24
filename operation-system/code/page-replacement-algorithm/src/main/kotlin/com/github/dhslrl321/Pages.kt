package com.github.dhslrl321

class Pages(
  private val maximumSize: Int,
  val pageMap: MutableMap<String, Page>,
) {

  fun getSize(): Int {
    return pageMap.size
  }

  fun put(newPage: Page) {
    check(pageMap.size < maximumSize) { IllegalStateException("페이지 최대 사이즈 초과") }
    pageMap[newPage.data] = newPage
  }

  fun get(queryData: String): Page? {
    return find(queryData)
  }

  private fun find(queryData: String) = pageMap[queryData]
}
