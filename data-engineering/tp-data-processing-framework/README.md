# Practices - Data engineering

## TP - Data processing with Apache Spark

To process a large amount of data partitioned on a data lake, you can use data processing frameworks such as Apache
Spark :

1. Read : https://spark.apache.org/docs/latest/sql-programming-guide.html

Some questions :

* What is Spark RDD API?
    * Spark RDD API (Resilient Distributed Datasets API) is the core API of Apache Spark that represents an immutable
      distributed collection of objects that can be processed in parallel across a cluster of machines. It provides
      low-level primitives for performing transformations and actions on the data, such as map, filter, reduce, join,
      and aggregate. RDDs are fault-tolerant and can recover lost data partitions by recomputing them from the original
      data source. The RDD API is the oldest API in Spark and has been replaced by the Dataset API in Spark 2.0, which
      provides a higher-level, strongly-typed API for working with structured and unstructured data. However, the RDD
      API is still widely used in Spark and provides a powerful and flexible programming model for distributed
      computing.
* What is Spark Dataset API?
    * Spark Dataset API is an extension of the Spark RDD API that provides a type-safe, object-oriented programming
      interface. It provides the benefits of both RDDs and DataFrames by allowing users to work with strongly typed,
      object-oriented data using familiar programming paradigms like Java generics and lambda expressions.
      With Spark Dataset API, users can perform complex data manipulation and analysis tasks using high-level functions
      and transformations, while still maintaining the benefits of distributed computing and fault tolerance provided by
      the Spark engine. It is built on top of the Spark SQL engine and supports many of the same operations as Spark
      DataFrames, but with the added benefits of strong typing and compile-time type checking.
* With which languages can you use Spark?
    * Java, Scala, Python and R. The core Spark implementation is written in Scala, but it also provides Java and Python
      APIs for ease of use. Additionally, R users can use the sparklyr package to work with Spark.
* Which data sources or data sinks can Spark work with?
  * Spark can work with a wide range of data sources and sinks, including:
    * File systems: HDFS, S3, local file system, etc.
    * Databases: MySQL, PostgreSQL, Oracle, etc.
    * Big data systems: Apache Cassandra, Apache HBase, Apache Kafka, etc.
    * Cloud storage services: Google Cloud Storage, Amazon S3, etc.
    * Streaming data: Apache Kafka, Flume, etc.
    
    Spark provides built-in connectors for many of these data sources and sinks, making it easy to read and write data 
    in various formats such as JSON, CSV, Parquet, and Avro. Additionally, Spark can integrate with other big data 
    processing tools such as Apache Hive and Apache Pig, enabling users to leverage the power of Spark within their 
    existing data processing pipelines.

### Analyse data with Apache Spark and Scala

One engineering team of your company created for you a TV News data stored as JSON inside the folder `data-news-json/`.

Your goal is to analyze it with your savoir-faire, enrich it with metadata, and store it
as [a column-oriented format](https://parquet.apache.org/).

1. Look at `src/main/scala/com/github/polomarcus/main/Main.scala` and update the code

**Important note:** As you work for a top-notch software company following world-class practices, and you care about
your project quality, you'll write a test for every function you write.

You can see tests inside `src/test/scala/` and run them with `sbt test`

### How can you deploy your app to a cluster of machines ?

* https://spark.apache.org/docs/latest/cluster-overview.html
  * To run on a cluster, the SparkContext can connect to several types of cluster managers (either Spark’s own 
  standalone cluster manager, Mesos, YARN or Kubernetes), which allocate resources across applications. Once connected, 
  Spark acquires executors on nodes in the cluster, which are processes that run computations and store data for your 
  application. Next, it sends your application code (defined by JAR or Python files passed to SparkContext) to the 
  executors. Finally, SparkContext sends tasks to the executors to run.
* https://spark.apache.org/docs/latest/submitting-applications.html
  * Bundle you application's dependencies - crete a package of dependencies used by a spark code such as .jar, .zip, .py
  or .egg files.
  * Launch application with spark-submit - once application is bundled it can be submitted to the cluster.
  * (optional) Submit application with specific configurations
  * Once you have deployed your application, the cluster mode overview describes the components involved in distributed 
  execution, and how to monitor and debug applications.


### Business Intelligence (BI)

How could use we Spark to display data on a BI tool such as [Metabase](https://www.metabase.com/) ?

Tips: https://github.com/polomarcus/television-news-analyser#spin-up-1-postgres-metabase-nginxand-load-data-to-pg

* It can be used to export data from spark to common database used by spark to write to, and used by metabase to read 
data which can be later visualized by creating a dashboard. In this case spark could be used for processing 
functionalities. 

### Continuous build and test

**Pro Tips** : https://www.scala-sbt.org/1.x/docs/Running.html#Continuous+build+and+test

Make a command run when one or more source files change by prefixing the command with ~. For example, in sbt shell try:

```bash
sbt
> ~ testQuick
```