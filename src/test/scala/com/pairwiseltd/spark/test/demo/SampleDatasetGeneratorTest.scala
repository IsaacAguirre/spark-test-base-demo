package com.pairwiseltd.spark.test.demo

import com.holdenkarau.spark.testing.{DatasetGenerator, DatasetSuiteBase}
import org.scalacheck.Arbitrary
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import org.scalacheck.Prop.forAll


class SampleDatasetGeneratorTest extends FunSuite with DatasetSuiteBase with Checkers {
  test("test generating Datasets[String]") {
    import sqlContext.implicits._

    val property =
      forAll(DatasetGenerator.genDataset[String](sqlContext)(Arbitrary.arbitrary[String])) {
        dataset => dataset.map(_.length).count() == dataset.count()
      }

    check(property)
  }

}
