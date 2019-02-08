package com.pairwiseltd.spark_test.demo

import org.apache.spark.sql.Dataset

trait SampleDatasetJob {
  def transform(input: Dataset[Person]): Dataset[Person] = {
    input.filter(p => p.age <= MAXIMUM_AGE && p.age > -1)
  }
}
