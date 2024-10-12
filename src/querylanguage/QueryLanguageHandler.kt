package com.tibame.tip101.group02.finalproject.healthhelper.util.querylanguage

object QueryLanguageHandler {
    private val separatorForPairs = "&"
    private val separatorBetweenKeyValue = "="
    fun tryToHandleStatement(
        statement: String
    ): Map<String, String>{
        return if(isValidStatement(statement)){
            handleStatement(statement)
        }else{
            emptyMap()
        }
    }

    private fun handleStatement(
        statement: String,
    ): Map<String, String>{
        val pairs = statement.split(separatorForPairs)
        var map = mutableMapOf<String,String>()

        pairs.forEach { pair ->
            val list1 = pair.split(separatorForPairs)
            map += list1[0] to list1[1]
        }
        return map
    }

    private fun isValidStatement(
        statement: String
    ): Boolean{
        if(statement.isBlank()) {
            return false
        }
        
        val onlyContainQueryLanguageExpressionSymbol = statement.all{
            val charString = it.toString()
            it.isLetter() || it.isDigit() || it == '_' || charString == separatorForPairs || charString == separatorBetweenKeyValue
        }

        if(!onlyContainQueryLanguageExpressionSymbol){
            return false
        }

        val pairs = statement.split("&")
        if(pairs.isEmpty()){
            return false
        }

        pairs.forEach { pair ->
            val list1 = pair.split(":")
            if(list1.size != 2){
                return false
            }
        }
        return true
    }
}