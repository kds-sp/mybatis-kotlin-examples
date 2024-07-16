package voucher

import org.apache.ibatis.annotations.Select

/**
 * 인수 개수가 컬럼의 개수와 동일한 생성자를 Mybatis가 자동으로 선택함
 */
interface VoucherMapper {
    @Select(
//        "select id, created_at, updated_at, applicable_plan_types, deactivate_reason, end_date_time, start_date_time, issued_count, registered_count, remain_count, is_activated, memo, name, prefix_code, is_expired",
        "select id, name, issued_count, registered_count, created_at, is_activated, memo",
        "from promotion where id = #{value}"
    )
    fun selectPromotionById(id: Int): PromotionDto

    @Select(
        "select id",
        "from promotion where id = #{value}"
    )
    fun selectPromotionById아이디만(id: Int): PromotionDto아이디만

    @Select(
        "select id, name, applicable_plan_types as 임의의컬럼명",
        "from promotion where id = #{value}"
    )
    fun selectPromotionByI아이디D와name과applicablePlanTypes(id: Int): PromotionDtoID와name과applicablePlanTypes
}