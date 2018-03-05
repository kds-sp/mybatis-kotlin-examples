package example04

import org.assertj.core.api.Assertions.*
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource
import org.apache.ibatis.jdbc.ScriptRunner
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.InputStreamReader
import java.sql.DriverManager
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class Example04Test {
    private lateinit var sqlSessionFactory: SqlSessionFactory

    @BeforeEach
    fun setup() {
        Class.forName(JDBC_DRIVER)
        val script = javaClass.getResourceAsStream("/CreateSimpleDB.sql")
        DriverManager.getConnection(JDBC_URL, "sa", "").use { connection ->
            val sr = ScriptRunner(connection)
            sr.setLogWriter(null)
            sr.runScript(InputStreamReader(script))
        }

        val ds = UnpooledDataSource(JDBC_DRIVER, JDBC_URL, "sa", "")
        val environment = Environment("test", JdbcTransactionFactory(), ds)
        val config = Configuration(environment)
        config.addMapper(Example04Mapper::class.java)
        sqlSessionFactory = SqlSessionFactoryBuilder().build(config)
    }

    @Test
    fun selectAddressById() {
        sqlSessionFactory.openSession().use { session ->
            val mapper = session.getMapper(Example04Mapper::class.java)

            val address = mapper.selectAddressById(1)

            assertThat(address.id).isEqualTo(1)
            assertThat(address.streetAddress).isEqualTo("123 Main Street")
            assertThat(address.city).isEqualTo("Bedrock")
            assertThat(address.state).isEqualTo("IN")

            assertThat(address.people.size).isEqualTo(3)

            assertThat(address.people[0].firstName).isEqualTo("Fred")
            assertThat(address.people[0].lastName).isEqualTo("Flintstone")
            assertThat(address.people[0].birthDate).isEqualTo(Date.from(LocalDate.of(1935, 2, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
            assertThat(address.people[0].employed).isTrue()
            assertThat(address.people[0].occupation).isEqualTo("Brontosaurus Operator")

            assertThat(address.people[1].firstName).isEqualTo("Wilma")
            assertThat(address.people[1].lastName).isEqualTo("Flintstone")
            assertThat(address.people[1].birthDate).isEqualTo(Date.from(LocalDate.of(1940, 2, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
            assertThat(address.people[1].employed).isTrue()
            assertThat(address.people[1].occupation).isEqualTo("Accountant")

            assertThat(address.people[2].firstName).isEqualTo("Pebbles")
            assertThat(address.people[2].lastName).isEqualTo("Flintstone")
            assertThat(address.people[2].birthDate).isEqualTo(Date.from(LocalDate.of(1960, 5, 6).atStartOfDay(ZoneId.systemDefault()).toInstant()))
            assertThat(address.people[2].employed).isFalse()
            assertThat(address.people[2].occupation).isNull()
        }
    }

    companion object {
        const val JDBC_URL = "jdbc:hsqldb:mem:aname"
        const val JDBC_DRIVER = "org.hsqldb.jdbcDriver"
    }
}
