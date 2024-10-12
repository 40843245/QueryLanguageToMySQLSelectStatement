package util.queryhandling.querymaker.operation.basicoperation.table.selectquery

import util.queryhandling.clausemaker.havingmaker.HavingBuilder
import util.queryhandling.clausemaker.havingsmaker.HavingsBuilder
import util.queryhandling.clausemaker.wheremaker.WhereBuilder
import util.queryhandling.clausemaker.wheresmaker.WheresBuilder

object SelectQueryBuilderTest {
    fun test1() {
        val queryBuilder = SelectQueryBuilder()
        val query = queryBuilder.from("table1")
            .column("column1", "column2")
            .where("column1 = value1")
            .build()
        println(query)
    }
    fun test2() {
        val whereBuilder1 = WhereBuilder()
        val whereCondition1 = whereBuilder1.key("key1").comparisonOperator("=").value("value1").build()
        val whereBuilder2 = WhereBuilder()
        val whereCondition2 = whereBuilder2.key("key2").comparisonOperator("<=").value("value2").build()
        val wheresBuilder = WheresBuilder()
        val whereConditionQuery = wheresBuilder.condition(whereCondition1, whereCondition2).logicalOperator("AND").build()

        val havingBuilder1 = HavingBuilder()
        val havingConnection1 = havingBuilder1.key("key1").comparisonOperator("=").value("value1").build()
        val havingBuilder2 = HavingBuilder()
        val havingConnection2 = havingBuilder2.key("key2").comparisonOperator("<=").value("value2").build()
        val havingsBuilder = HavingsBuilder()
        val havingConditionQuery =
            havingsBuilder.condition(havingConnection1, havingConnection2).logicalOperator("AND").build()

        val queryBuilder = SelectQueryBuilder()
        val query = queryBuilder.from("table1")
            .column("column1", "column2")
            .where(whereConditionQuery)
            .having(havingConditionQuery)
            .limit(1u)
            .offset(2u)
            .build()

        println(query)
    }
}
