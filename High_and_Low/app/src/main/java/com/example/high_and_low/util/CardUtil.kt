package com.example.high_and_low.util

public fun calcHiddenCard(shownCard: Int, maxNum: Int): Int {
    return (((shownCard + 1)..(shownCard + (maxNum - 1))).random() - 1) % maxNum + 1
}

public fun calcCardName(num: Int): String {
    val numStr = num.toString().padStart(2, '0')
    return "card_spade_${numStr}"
}
