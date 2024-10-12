package util.queryhandling.querymaker.base

abstract class BaseQuery {
    protected val columns: MutableList<String> = ArrayList()
    protected val values : MutableList<String> = ArrayList()
    protected val tables: MutableList<String> = ArrayList()
    protected var condition: String = ""
    protected var database:String = ""
    protected var ifExists : Boolean = false

    open fun column(vararg column: String): BaseQuery {
        assert(column.isNotEmpty())
        this.columns.addAll(column)
        return this
    }

    open fun from(vararg table: String): BaseQuery {
        assert(table.isNotEmpty())
        this.tables.addAll(table)
        return this
    }

    open fun table(table: String): BaseQuery {
        this.tables.clear()
        this.tables.add(table)
        return this
    }

    open fun where(condition: String): BaseQuery {
        assert(condition.isNotBlank())
        this.condition = condition
        return this
    }

    open fun values(vararg value:String): BaseQuery {
        assert(value.isNotEmpty())
        this.values.addAll(value)
        return this
    }

    open fun database(database:String): BaseQuery {
        assert(database.isNotBlank())
        this.database = database
        return this
    }

    open fun ifExists(ifExists : Boolean = false): BaseQuery {
        this.ifExists = ifExists
        return this
    }

    fun assertWhenColumnSizeNotEqValueSize(){
        assert(this.columns.size == this.values.size)
    }
    
    abstract fun build():String
}