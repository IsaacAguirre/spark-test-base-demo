package com.pairwiseltd.spark_test.demo

import com.holdenkarau.spark.testing.{DatasetGenerator, DatasetSuiteBase}
import org.apache.spark.sql.Dataset
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import org.scalacheck.Prop.forAll


class SampleDatasetGeneratorTest extends FunSuite with SampleDatasetJob  with DatasetSuiteBase with Checkers {
  test(s"test all the resulting people should be under $MAXIMUM_AGE years old") {
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
        dataset => transform(dataset).collect.forall(p => p.age <= MAXIMUM_AGE && p.age >= 0)
      }

    check(property)
  }

}
