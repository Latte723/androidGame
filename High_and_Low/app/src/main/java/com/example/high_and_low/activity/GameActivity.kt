package com.example.high_and_low.activity

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.high_and_low.R
import com.example.high_and_low.`object`.CountPreference
import com.example.high_and_low.data.Card

class GameActivity : BackHomeActivity() {

    /** BGM管理 */
    private lateinit var mediaPlayer: MediaPlayer
    /** 効果音管理 */
    private lateinit var soundPool: SoundPool
    // カード関係
    private var seWin: Int = 0
    private var seLose: Int = 0
    private val maxNum = 13
    private lateinit var card: Card
    // レイアウト関係
    private lateinit var winView: TextView
    private lateinit var loseView: TextView
    private lateinit var buttonStart: Button
    private lateinit var buttonRestart: Button
    /** ゲーム判定 */
    private enum class HighLow {
        HIGH, LOW
    }
    // count関係
    private var winCount: Int = 0
    private var loseCount: Int = 0
    private var straightCount: Int = 0
    private var matchCount: Int = 0
    private lateinit var countPref:CountPreference

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_view)

        // 効果音の設定
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(1)
            .build()
        seWin = soundPool.load(this, R.raw.win, 1)
        seLose = soundPool.load(this, R.raw.lose, 1)

        // BGMの設定
        mediaPlayer = MediaPlayer.create(this, R.raw.airport_lounge)
        mediaPlayer.isLooping = true
        mediaPlayer.setVolume(0.1f, 0.1f)
        mediaPlayer.start()

        winView = findViewById<TextView>(R.id.win)
        loseView = findViewById<TextView>(R.id.lose)
        buttonStart = findViewById(R.id.game_start)
        buttonRestart = findViewById(R.id.game_restart)
        winView.visibility = View.INVISIBLE
        loseView.visibility = View.INVISIBLE
        buttonRestart.visibility = View.INVISIBLE

        // CountDataの取得
        countPref = CountPreference(this)
        winCount = countPref.getWinCount()
        loseCount = countPref.getLoseCount()
        straightCount = countPref.getStraightCount()
        matchCount = countPref.getMatchCount()

        // straightCountの表示
        findViewById<TextView>(R.id.straightCount).setText(straightCount.toString())
    }

    private fun initCard() {
        card = Card((1..maxNum).random(), maxNum, resources, packageName)
        findViewById<ImageView>(R.id.hiddenCard).setImageResource(R.drawable.card_00)
        findViewById<ImageView>(R.id.shownCard).setImageResource(card.shownCardId)
    }

    /** ゲーム開始処理 */
    public fun onGameStart(view: View?) {
        buttonStart.visibility = View.INVISIBLE
        initCard()
    }

    /** HIGHボタンを押したときの処理 */
    public fun onClickHigh(view: View?) {
        judge(HighLow.HIGH)
    }

    /** LOWボタンを押したときの処理 */
    public fun onClickLow(view: View?) {
        judge(HighLow.LOW)
    }

    /** TryAgainボタンを押したときの処理 */
    public fun onTryAgain(view: View?) {
        buttonRestart.visibility = View.INVISIBLE
        winView.visibility = View.INVISIBLE
        loseView.visibility = View.INVISIBLE
        initCard()
    }

    /** ゲームの判定処理 */
    private fun judge(highLow: HighLow) {
        findViewById<ImageView>(R.id.hiddenCard).setImageResource(card.hiddenCardId)
        val higher = card.hiddenCard > card.shownCard
        if ((higher && HighLow.HIGH == highLow) || (!higher && HighLow.LOW == highLow)) {
            winView.visibility = View.VISIBLE
            soundPool.play(seWin, 0.01f, 0.01f, 0, 0, 1.0f)
            winCount += 1
            straightCount +=1
        } else {
            loseView.visibility = View.VISIBLE
            soundPool.play(seLose, 0.05f, 0.05f, 0, 0, 1.0f)
            loseCount += 1
            straightCount = 0
        }
        matchCount = winCount + loseCount
        buttonRestart.visibility = View.VISIBLE
        countPref.putCount(winCount, loseCount, straightCount, matchCount)
        findViewById<TextView>(R.id.straightCount).setText(straightCount.toString())
    }

    /** 画面遷移時の処理 */
    public override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }
}