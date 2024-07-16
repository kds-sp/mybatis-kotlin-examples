package voucher

import org.assertj.core.api.Assertions.*
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource
import org.apache.ibatis.jdbc.ScriptRunner
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.InputStreamReader
import java.sql.DriverManager

/**
 * 메소드를 각각 따로 실행해야 함
 */
class VoucherTest {
    companion object {
        const val JDBC_URL = "jdbc:hsqldb:mem:aname"
        const val JDBC_DRIVER = "org.hsqldb.jdbcDriver"
    }

    private fun newSession(): SqlSession {
        Class.forName(JDBC_DRIVER)
        val script = javaClass.getResourceAsStream("/CreateSimpleDB.sql")
        DriverManager.getConnection(JDBC_URL, "sa", "").use { connection ->
            val sr = ScriptRunner(connection)
            sr.setLogWriter(null)
            sr.runScript(InputStreamReader(script!!))
        }

        val ds = UnpooledDataSource(JDBC_DRIVER, JDBC_URL, "sa", "")
        val environment = Environment("test", JdbcTransactionFactory(), ds)
        val config = Configuration(environment)
        config.addMapper(VoucherMapper::class.java)
        return SqlSessionFactoryBuilder().build(config).openSession()
    }

    @Test
    fun selectVoucherWithAllFields() {
        newSession().use { session ->
            val mapper = session.getMapper(VoucherMapper::class.java)

            val voucher = mapper.selectPromotionById(1)

            assertThat(voucher.id).isEqualTo(1)
            assertThat(voucher.memo).isEqualTo("memo")
        }
    }


    @Test
    fun selectVoucherWithAllFields아이디만() {
        newSession().use { session ->
            val mapper = session.getMapper(VoucherMapper::class.java)

            val voucher = mapper.selectPromotionById아이디만(1)

            assertThat(voucher.id).isEqualTo(1)
        }
    }

    @Test
    fun selectPromotionByI아이디와name과applicablePlanTypesTest() {
        newSession().use { session ->
            val mapper = session.getMapper(VoucherMapper::class.java)

            val voucher = mapper.selectPromotionByI아이디D와name과applicablePlanTypes(1)

            assertThat(voucher.id).isEqualTo(1)
            assertTrue(voucher.applicablePlanTypes.equals(listOf("A","B")))

            println("name=${voucher.name}")
        }

    }
}