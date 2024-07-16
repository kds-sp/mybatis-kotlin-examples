package voucher

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class PromotionDto(
    val id: Long,
    val name: String,
//    val applicablePlanTypes: List<String>,
    val issuedCount: Int,
    val registeredCount: Int = 0,
//    val term: TermDto,
    val issuedDate: String?,
    var isActivated: String,
    val memo: String?
)

open class PromotionDto아이디만(
    val id: Long,
    val someValue: Long,
) {
    constructor(id: Long) : this(id, id) {}
}