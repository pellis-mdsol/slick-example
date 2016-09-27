package database

import database.model.Example
import util.{BaseFunSpec, TestFutures}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

class ExampleRepositoryIntegrationTest extends BaseFunSpec with TestFutures with TestDatabase {

  val repo = new ExampleRepository

  describe("ExampleRepository") {
    describe("#save"){
      it("should store an example") {

        val example = Example(Random.nextInt, "Test")

        val result = repo.save(example).futureValue

        result shouldBe 1
      }

      it("should update an example if exist") {
        val original = Example(Random.nextInt, "Test")
        val current = Example(original.id, "New Test")

        repo.save(original).futureValue
        val result = repo.save(current).futureValue

        result shouldBe 1
      }
    }

    describe("#findById") {
      it("should find an example by id when exist") {
        val example = Example(Random.nextInt, "Test")

        repo.save(example).futureValue

        val result = repo.findById(example.id).futureValue

        result shouldBe Some(example)
      }

      it("should returns None when the exampledoesn't exist"){
        val result = repo.findById(Random.nextInt).futureValue

        result shouldBe None
      }
    }

    describe("#delete") {
      it("should delete an example") {
        val template = Example(Random.nextInt, "Test")

        repo.save(template).futureValue shouldBe 1

        val result = repo.delete(template.id).futureValue

        result shouldBe 1
      }
    }
  }
}
