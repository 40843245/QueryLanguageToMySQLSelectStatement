package util.queryhandling.querymaker.operation.basicoperation.table.selectquery

import util.queryhandling.querymaker.base.BaseQuery

/**
 * SelectQueryBuilder
 *
 * extends BaseQuery
 *
 * @constructor A builder that create `select` statement from table about MySQL
 */
class SelectQueryBuilder(): BaseQuery() {
    private val groupColumns: MutableList<String> = ArrayList()
    private var having: String = ""
    private var order: String = ""
    private var limit : UInt = 0u
    private var offset : UInt = 0u

    override fun column(vararg column: String): SelectQueryBuilder {
        super.column(*column)
        return this
    }

    override fun from(vararg table: String): SelectQueryBuilder {
        super.from(*table)
        return this
    }

    override fun where(condition: String): SelectQueryBuilder {
        super.where(condition)
        return this
    }

    fun order(order: String): SelectQueryBuilder {
        assert(order.isNotBlank())
        this.order = order
        return this
    }

    fun groupColumns(vararg groupColumn:String): SelectQueryBuilder {
        assert(groupColumn.isNotEmpty())
        this.groupColumns.addAll(groupColumn)
        return this
    }

    fun having(having: String): SelectQueryBuilder {
        assert(having.isNotBlank())
        this.having = having
        return this
    }

    fun limit(limit:UInt): SelectQueryBuilder {
        this.limit = limit
        return this
    }

    fun offset(offset:UInt): SelectQueryBuilder {
        this.offset = offset
        return this
    }

    override fun build(): String {

        val stringBuilder = StringBuilder()

        stringBuilder.append("SELECT ")
        stringBuilder.append(this.columns[0])
        for (i in 1..this.columns.size-1) {
            stringBuilder.append(", ")
            stringBuilder.append(this.columns[i])
        }

        stringBuilder.append(" FROM ")
        stringBuilder.append(this.tables[0])
        for (i in 1..this.tables.size-1) {
            stringBuilder.append(", ")
            stringBuilder.append(this.tables[i])
        }

        if(this.condition.trim().isNotBlank()){
            stringBuilder.append(" WHERE ")
            stringBuilder.append(this.condition)
        }

        if(this.groupColumns.isNotEmpty()){
            stringBuilder.append(" GROUP BY ")
            stringBuilder.append(this.groupColumns[0])
            for (i in 1..this.groupColumns.size-1) {
                stringBuilder.append(", ")
                stringBuilder.append(this.groupColumns[i])
            }
        }

        if(this.having.trim().isNotBlank()){
            stringBuilder.append(" HAVING ")
            stringBuilder.append(this.having)
        }

        if (this.limit != 0u){
            stringBuilder.append(" LIMIT ")
            stringBuilder.append(this.limit)
        }

        if (this.offset != 0u){
            stringBuilder.append(" OFFSET ")
            stringBuilder.append(this.offset)
        }

        stringBuilder.append(" ;")
        return stringBuilder.toString()
    }
}