package example01

import java.time.LocalDate
import java.time.LocalDateTime

class UseTest {
    fun useTest() {
        val person = Person(
            0,
            "firstName",
            "lastName",
            LocalDate.now(),
            "employed",
            null
            )
    }
}