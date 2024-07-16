package voucher

import org.apache.ibatis.annotations.Select

interface VoucherMapper {
    @Select(
        "select id, created_at, updated_at, applicable_plan_types, deactivate_reason, end_date_time, start_date_time, issued_count, registered_count, remain_count, is_activated, memo, name, prefix_code, is_expired",
        "from promotion where id = #{value}"
    )
    fun selectPromotionById(id: Int): PromotionDto
}