package com.erikaosgue.breathapp

import android.app.Activity
import android.content.Intent
import android.icu.text.MessageFormat
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import com.erikaosgue.breathapp.databinding.ActivityMainBinding
import com.erikaosgue.breathapp.util.Prefs
import com.github.florent37.viewanimator.ViewAnimator

class MainActivity : AppCompatActivity() {

	lateinit var prefs: Prefs



	lateinit var  binding: ActivityMainBinding
 	@RequiresApi(Build.VERSION_CODES.N)
	override fun onCreate(savedInstanceState: Bundle?) {
 		super.onCreate(savedInstanceState)
 		binding = ActivityMainBinding.inflate(layoutInflater)
 		setContentView(binding.root)

		prefs = Prefs(this)

		binding.apply {

			breathsText.text = MessageFormat.format("{0} min today", prefs.sessions)
			todayMinutesText.text = MessageFormat.format("{0} breathes", prefs.breathes)
			lastBreathText.text = prefs.date

//			chronometerId.apply {
//				isCountDown = true
//				base = SystemClock.elapsedRealtime() + 100000
//				start()
//			}
		}

		binding.startButton.setOnClickListener{
			startAnimation()
		}

	}
	private fun startAnimation(){
		ViewAnimator
			.animate(binding.imageView)
			.alpha(0f, 1f)
			.onStart {
				binding.guideText.text = "Inhale.... Exhale"
			}
			.decelerate()
			.duration(1000)
			.thenAnimate(binding.imageView)
			.scale(0.02f, 1.5f, 0.02f)
			.rotation(360f)
			.repeatCount(5)
			.accelerate()
			.duration(5000)
			.onStop {
				binding.apply {
					guideText.text = "Good Job"
					imageView.scaleX = 1.0f
					imageView.scaleY = 1.0f
				}

				prefs.sessions = prefs.sessions + 1
				prefs.breathes = prefs.breathes + 1
				prefs.setDate(System.currentTimeMillis())

                val handler = Handler()
				val countDownTimer = Runnable {
					startActivity(Intent( this@MainActivity, MainActivity::class.java))
					finish()
				}

				handler.postDelayed(countDownTimer, 100)
			}.start()
	}

	private fun startIntroAnimation(){
		ViewAnimator
			.animate(binding.guideText)
			.scale(0F, 1F)
			.duration(1500L)
			.onStart {
				binding.guideText.text = "Breathe"

			}
//			.rotation(360)
			.start()
	}
}