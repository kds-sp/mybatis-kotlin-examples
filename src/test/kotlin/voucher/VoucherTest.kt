package voucher

import org.assertj.core.api.Assertions.*
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource
import org.apache.ibatis.jdbc.ScriptRunner
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.junit.jupiter.api.Test
import java.io.InputStreamReader
import java.sql.DriverManager
import java.time.LocalDate

class VoucherTest {
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
    fun selectPersonWithAllFields() {
        newSession().use { session ->
            val mapper = session.getMapper(VoucherMapper::class.java)

            val voucher = mapper.selectPromotionById(1)

            assertThat(voucher.id).isEqualTo(1)
        }
    }

    companion object {
        const val JDBC_URL = "jdbc:hsqldb:mem:aname"
        const val JDBC_DRIVER = "org.hsqldb.jdbcDriver"
    }
}