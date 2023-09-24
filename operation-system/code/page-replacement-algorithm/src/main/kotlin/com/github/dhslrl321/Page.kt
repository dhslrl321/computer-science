package com.github.dhslrl321

import java.time.Instant

data class Page(
  val data: String,
  var age: Instant = Instant.now(),
) {

  fun data(): String {
    used()
    return data;
  }

  private fun used() {
    age = Instant.now()
  }
}
