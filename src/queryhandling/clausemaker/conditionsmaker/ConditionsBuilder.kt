package util.queryhandling.clausemaker.conditionsmaker

/**
 * WheresBuilder
 *
 * @constructor A builder that create a complex condition (which may consists of a few simple conditions ) about MySQL.
 *              It can be concatenated after `WHERE` clause
 */

open class ConditionsBuilder {

    private val conditions: MutableList<String> = ArrayList()
    private val logicalOperators: MutableList<String> = ArrayList()

    fun condition(vararg condition:String): ConditionsBuilder {
        this.conditions.addAll(condition)
        return this
    }

    fun condition(conditions:List<String>): ConditionsBuilder {
        this.conditions.addAll(conditions)
        return this
    }

    fun logicalOperator(vararg logicalOperator:String): ConditionsBuilder {
        this.logicalOperators.addAll(logicalOperator)
        return this
    }

    fun logicalOperator(logicalOperators:List<String>): ConditionsBuilder {
        this.logicalOperators.addAll(logicalOperators)
        return this
    }

    fun build():String {
        assert(this.conditions.isNotEmpty())
        assert(this.logicalOperators.isNotEmpty())
        assert(this.conditions.size == this.logicalOperators.size + 1)

        val stringBuilder = StringBuilder()

        stringBuilder.append(this.conditions[0])
        for(i in 1..this.conditions.size-1){
            stringBuilder.append(" ")
            stringBuilder.append(this.logicalOperators[i-1])
            stringBuilder.append(" ")
            stringBuilder.append(this.conditions[i])
            stringBuilder.append(" ")
        }

        return stringBuilder.toString()
    }
}