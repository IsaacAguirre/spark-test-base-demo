package com.pairwiseltd.spark.test.demo

import com.holdenkarau.spark.testing.{DatasetGenerator, DatasetSuiteBase}
import org.apache.spark.sql.Dataset
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import org.scalacheck.Prop.forAll


class SampleDatasetGeneratorTest extends FunSuite with DatasetSuiteBase with Checkers {
  test("test generating Datasets[String]") {
    import sqlContext.implicits._

    val personGen: Gen[Dataset[Person]] =
      DatasetGenerator.genDataset[Person](sqlContext) {
        val generator: Gen[Person] = for {
          name <- Arbitrary.arbitrary[String]
          speed <- Arbitrary.arbitrary[Byte]
        } yield (Person(name, speed))

        generator
      }
    val property =
      forAll(personGen) {
        dataset => dataset.map(_.age).count() == dataset.count()
      }

    property.check
  }

}


case class Person(name: String, age: Byte /* a person not a vampire*/)