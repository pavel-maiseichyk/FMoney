package com.pm.savings.presentation.core

sealed class AppColors(val value: Long) {
    object Red : AppColors(0xFFE74C3C)
    object Green : AppColors(0xFF008080)
    object Blue : AppColors(0xFF005792)
    object Yellow : AppColors(0xFFFBB204)
    object Purple : AppColors(0xFF5A56C2)

    companion object {
        fun getRandomColor(): Long {
            return when ((0..4).random()) {
                0 -> Red.value
                1 -> Green.value
                2 -> Blue.value
                3 -> Yellow.value
                else -> Purple.value
            }
        }

        fun Long.getLighterColor(): Long {
            return this.toString().replace("FF", "80").toLong()
        }

        fun changeColor(existing: Long): Long {
            val color = when ((0..4).random()) {
                0 -> Red.value
                1 -> Green.value
                2 -> Blue.value
                3 -> Yellow.value
                else -> Purple.value
            }
            if (color != existing) return color
            return changeColor(existing)
        }
    }
}
