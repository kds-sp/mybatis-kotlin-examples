package voucher

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Schema(description = "프로모션 객체 구조")
open class PromotionDto(
    @Schema(description = "프로모션 고유식별번호", example = "1234")
    val id: Long,
    @Schema(description = "프로모션 이름", example = "오늘의 프로모션")
    val name: String,
    @Schema(description = "프로모션 사용이 가능한 서비스", example = "[\"Studio Mastering with AI\", \"Studio Mastering\"]")
    val applicablePlanTypes: List<String>,
    @Schema(description = "발급된 프로모션 코드의 갯수", example = "100")
    val issuedCount: Int,
    @Schema(description = "발급된 프로모션 코드 중 등록된 갯수", example = "30")
    val registeredCount: Int = 0,
    @Schema(description = "프로모션 유효기간을 가진 객체 구조")
    val term: TermDto,
    @Schema(description = "프로모션 생성일", example = "2024-02-10 10:00:00")
    val issuedDate: String?,
    @Schema(description = "프로모션의 현재 활성화 여부", example = "Y 또는 N")
    var isActivated: String,
    @Schema(description = "프로모션의 메모", example = "테스트 바우처입니다.")
    val memo: String?
)