package com.example.high_and_low.data

import android.content.res.Resources
import com.example.high_and_low.util.calcCardName
import com.example.high_and_low.util.calcHiddenCard

data class Card(
    val shownCard: Int,
    val hiddenCard: Int,
    var shownCardName: String = "",
    var hiddenCardName: String = "",
    var shownCardId: Int = 0,
    var hiddenCardId: Int = 0
) {
    constructor(shownCard: Int, maxNum: Int, resources: Resources, packageName: String): this(
        shownCard,
        calcHiddenCard(shownCard, maxNum)
    ) {
        if (hiddenCard == shownCard) {
            throw error("2枚のカードの値が同じです。 shownCard:$shownCard, hiddenCard:$hiddenCard")
        }
        shownCardName = calcCardName(shownCard)
        hiddenCardName = calcCardName(hiddenCard)
        shownCardId = resources.getIdentifier(shownCardName, "drawable", packageName)
        hiddenCardId = resources.getIdentifier(hiddenCardName, "drawable", packageName)
    }
}