import org.apache.spark.sql.*;


public class Analysis {
    public static void main(String[] args) {
        System.setProperty("hadoop.home.dir", "C:\\winutils");
        SparkSession sparkSession=SparkSession.builder().appName("SparkSql").master("local").getOrCreate();
        Dataset<Row> raw_Data = sparkSession.read().json("C:\\Users\\Zehra\\Desktop\\Data\\search.json");
        raw_Data.createOrReplaceTempView("searchdata");


        //MOST SEARCHED PRODUCTS-TOP 10
        // Dataset<Row> search_topTenProduct = sparkSession.sql("select search,count(search) count from searchdata group by search order by count desc limit 10");
        Dataset<Row> top10Product = raw_Data.groupBy(raw_Data.col("search")).count().sort(functions.desc("count")).limit(10);

        //TOP 10 CITIES
        //Dataset<Row>  search_topTenCity= sparkSession.sql("select city,count(city) cityCount from searchdata group by city order by cityCount desc limit 10");
        Dataset<Row> top10City = raw_Data.groupBy(raw_Data.col("city")).count().sort(functions.desc("count")).limit(10);
        //top10City.show();

        //MOST SEARCHED PRODUCTS-LAST 1 DAY
        Dataset<Row> search_last24Hours = sparkSession.sql("SELECT  * FROM searchdata WHERE  current_ts >= NOW() - INTERVAL 1 DAY ORDER BY current_ts");
        raw_Data.filter(functions.col("current_ts").$greater(functions.lit(functions.date_sub(functions.current_date(), 1))));


        //SEARCH COUNT per CUSTOMER, WHICH PRODUCTS CUSTOMER SEARCHED
         Dataset<Row> search_count=sparkSession.sql("select userId,count(userId),collect_list(search) from searchdata group by userId order by userId asc ");
         raw_Data.groupBy("userId").agg(functions.count("userId").as("search count"),functions.collect_list("search").as("searched products")).show(false);

    }
}
