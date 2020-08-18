package com.example.high_and_low.`object`

import android.content.Context
import android.content.SharedPreferences

class CountPreference {
    // prefの値
    private val COUNT_PREF_NAME = "CountData"
    private val WIN_COUNT_KEY = "winCount"
    private val LOSE_COUNT_KEY = "loseCount"
    private val STRAIGHT_KEY = "straight"
    private val MATCH_COUNT_KEY = "matchCount"
    private var pref: SharedPreferences

    constructor(context: Context) {
        pref = context.getSharedPreferences(COUNT_PREF_NAME, Context.MODE_PRIVATE)
    }

    /** CountPreferenceへの値の設定 */
    private fun putInt(key: String, value: Int) {
        pref.edit().putInt(key, value)
    }

    /** Countの保存 */
    public fun putCount(winCount: Int, loseCount: Int, straightCount: Int, matchCount: Int) {
        putInt(WIN_COUNT_KEY, winCount)
        putInt(LOSE_COUNT_KEY, loseCount)
        putInt(STRAIGHT_KEY, straightCount)
        putInt(MATCH_COUNT_KEY, matchCount)
        pref.edit().apply()
    }

    /** QinCountの取得 */
    public fun getWinCount(): Int {
        return pref.getInt(WIN_COUNT_KEY, 0)
    }

    /** LoseCountの取得 */
    public fun getLoseCount(): Int {
        return pref.getInt(LOSE_COUNT_KEY, 0)
    }

    /** StraightCountの取得 */
    public fun getStraightCount(): Int {
        return pref.getInt(STRAIGHT_KEY, 0)
    }

    /** MatchCountの取得 */
    public fun getMatchCount(): Int {
        return pref.getInt(MATCH_COUNT_KEY, 0)
    }
}
