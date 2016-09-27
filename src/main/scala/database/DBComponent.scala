package database

import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile

trait DBComponent {
  implicit val dc: DatabaseConfig[JdbcProfile]

  val driver: JdbcProfile = dc.driver

  import driver.api._

  val db: Database = dc.db
}
