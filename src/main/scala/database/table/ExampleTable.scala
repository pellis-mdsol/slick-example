package database.table

import database.model.Example
import database.DBComponent

trait ExampleTable {
  this: DBComponent =>

  import driver.api._

  class Examples(tag: Tag) extends Table[Example](tag, "examples") {

    def id = column[Int]("id", O.PrimaryKey)

    def text = column[String]("content")

    override def * = (id, text) <> (Example.tupled, Example.unapply)
  }

  protected val examples = TableQuery[Examples]

}
