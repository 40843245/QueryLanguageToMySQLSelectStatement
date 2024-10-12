package querylanguagetomysqlstatement

import com.tibame.tip101.group02.finalproject.healthhelper.util.querylanguage.QueryLanguageHandler
import util.queryhandling.clausemaker.conditionmaker.ConditionBuilder
import util.queryhandling.clausemaker.conditionsmaker.ConditionsBuilder

class QueryLanguageToMySQLStatementHandler(
    val queryLanguageStatement: String,
) {
    fun convertQueryLanguageToMySQLWhereStatement(): String {
        val queryMap = QueryLanguageHandler.tryToHandleStatement(queryLanguageStatement)
        if (queryMap.isEmpty()) {
            return ""
        }

        val keys = queryMap.keys.toList()
        val values = queryMap.values.toList()
        val logicalOperators = List<String>(keys.size-1){"AND"}

        var mySQLConditionsBuilder = ConditionsBuilder()
        mySQLConditionsBuilder = mySQLConditionsBuilder.logicalOperator(logicalOperators)

        keys.forEachIndexed { index, _ ->
            val key = keys[index]
            val comparisonOperator = "="
            val value = values[index]


            val mySQLConditionBuilder = ConditionBuilder()
            val conditionQuery = mySQLConditionBuilder
                .key(key)
                .comparisonOperator(comparisonOperator)
                .value(value)
                .build()

            mySQLConditionsBuilder = mySQLConditionsBuilder.condition(conditionQuery)
        }

        val conditionsQuery = mySQLConditionsBuilder.build()

        return conditionsQuery
    }
}