package util.queryhandling.clausemaker.conditionmaker

import util.string.patternizeValue

/**
 * ConditionBuilder
 *
 * @constructor A builder that create a simple condition about MySQL
 */

open class ConditionBuilder {
    private var key : String = ""
    private var comparisonOperator : String = ""
    private var value : String = ""

    fun key(key:String): ConditionBuilder {
        assert(key.isNotEmpty())
        this.key = key
        return this
    }

    fun comparisonOperator(comparisonOperator:String): ConditionBuilder {
        assert(comparisonOperator.isNotEmpty())
        this.comparisonOperator = comparisonOperator
        return this
    }

    fun value(value:String): ConditionBuilder {
        assert(value.isNotEmpty())
        this.value = value.patternizeValue()
        return this
    }

    fun build(): String{
        val stringBuilder = StringBuilder()
        stringBuilder.append(this.key)
        stringBuilder.append(" ")
        stringBuilder.append(this.comparisonOperator)
        stringBuilder.append(" ")
        stringBuilder.append(this.value)

        return stringBuilder.toString()
    }
}