package database

import com.typesafe.scalalogging.LazyLogging
import database.model.Example
import database.table.ExampleTable
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

trait Repository[T] {
  def save(data: T): Future[Int]
  def findById(id: Int): Future[Option[T]]
  def delete(id: Int): Future[Int]
}

class ExampleRepository(implicit val dc: DatabaseConfig[JdbcProfile], executionContext: ExecutionContext) extends Repository[Example] with ExampleTable with DBComponent with LazyLogging{

  import driver.api._

  override def save(example: Example): Future[Int] = db.run(examples insertOrUpdate example) andThen {
    case Success(s) => logger.info(s"Service ${example.id} saved.")
    case Failure(ex) => logger.error(ex.getMessage)
  }

  override def findById(id: Int): Future[Option[Example]] = db.run(examples.filter(_.id === id).result.headOption) andThen {
    case Success(Some(s)) => logger.info(s"Found template for id $id")
    case Success(None) => logger.info(s"No template found for id $id")
    case Failure(ex) => logger.error(ex.getMessage)
  }

  override def delete(id: Int): Future[Int] = db.run(examples.filter(_.id === id).delete) andThen {
    case Success(s) => logger.info(s"Template ${id.toString} is deleted")
    case Failure(ex) => logger.error(ex.getMessage)
  }

}
