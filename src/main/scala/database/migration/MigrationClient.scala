package database.migration

import database.config.DBConfig
import org.flywaydb.core.Flyway

object MigrationClient {

  def migrate(url: String, user: String, password: String, migration: String = "db.migration"): Unit = {
    val flyway: Flyway = new Flyway
    flyway.setDataSource(url, user, password)
    flyway.setLocations(migration)
    flyway.migrate
  }

  def migrate(implicit config: DBConfig): Unit  = {
    migrate(config.url, config.user, config.password, config.migration)
  }
}