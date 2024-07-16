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

open class PromotionDtoID와name과applicablePlanTypes (
    val id: Long,
    val name: String,
    val applicablePlanTypespes: List<String>,
) {
    constructor(id: Long, name: String, applicablePlanTypes:String) : this(
        id,
        name,
        applicablePlanTypes.split(",")
    )

    /**
     * 인수 개수가 동일한 생성자가 여러개 있을 때 어떤 생성자가 호출될지 알 수 없음
     */
//    constructor(name: String, id: Long, applicablePlanTypes:String) : this(
//        id,
//        name = "임의의 이름",
//        applicablePlanTypes.split(",")
//    )
}