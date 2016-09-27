package database

import com.typesafe.config.ConfigFactory
import database.config.DBConfig
import database.migration.MigrationClient
import org.scalatest.{BeforeAndAfterAll, Suite}
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile

trait TestDatabase extends Suite with BeforeAndAfterAll{

  val config = ConfigFactory.load("database.conf")

  implicit val dbConfig = DBConfig.load("h2", config)

  implicit val dc = DatabaseConfig.forConfig[JdbcProfile]("h2", config)

  override def beforeAll(): Unit = {
    MigrationClient.migrate
  }
}