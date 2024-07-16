package voucher

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "바우처 윺효기간을 가진 객체 구조")
data class TermDto(
    @Schema(description = "프로모션 유효기간의 시작일", example = "2024-03-01 00:00:00")
    val expirationStartDate: String,
    @Schema(description = "프로모션 유효기간의 종료일", example = "2024-03-30 23:59:59")
    val expirationEndDate: String,
)
