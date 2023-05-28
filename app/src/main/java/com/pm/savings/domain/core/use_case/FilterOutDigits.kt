package com.pm.savings.domain.core.use_case

class FilterOutDigits {

    fun execute(string: String): Pair<Boolean, String?> {
        var outcome = string
        if (!outcome.all { char -> char.isDigit() || char == '.' || char == ',' }) return Pair(
            false,
            null
        )
        if (outcome.isNotEmpty())
            if (outcome.last() == ',') outcome = outcome.replace(",", ".")
        if (outcome.contains(".") && outcome.substringAfter(".").contains(".")) return Pair(
            false,
            null
        )
        if (outcome.contains(".") && outcome.substringAfter(".").length > 2) return Pair(
            false,
            null
        )
        if (outcome.substringBefore('.').length > 10) return Pair(false, null)

        return Pair(true, outcome)
    }
}