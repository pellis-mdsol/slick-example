package database.config

import com.typesafe.config.Config

case class DBConfig(url: String, user: String, password: String, migration: String)

object DBConfig {

  val URL_KEY = "db.url"
  val USER_KEY = "db.user"
  val PASSWORD_KEY = "db.password"
  val MIGRATION_KEY = "db.migration"

  def load(path: String, config: Config): DBConfig = DBConfig(
        url = config.getString(s"$path.$URL_KEY"),
        user = config.getString(s"$path.$USER_KEY"),
        password = config.getString(s"$path.$PASSWORD_KEY"),
        migration = config.getString(s"$path.$MIGRATION_KEY")
      )
}

