package com.erikaosgue.breathapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.erikaosgue.breathapp.databinding.ActivityMainBinding
import com.github.florent37.viewanimator.ViewAnimator

class MainActivity : AppCompatActivity() {
	lateinit var  binding: ActivityMainBinding
 	override fun onCreate(savedInstanceState: Bundle?) {
 		super.onCreate(savedInstanceState)
 		binding = ActivityMainBinding.inflate(layoutInflater)
 		setContentView(binding.root)

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

				//Refresh the Activity to put the new data
				object : CountDownTimer(2000, 1000){
					override fun onTick(millisUntilFinished: Long) {
						TODO("Not yet implemented")
					}

					override fun onFinish() {
						startActivity(Intent(applicationContext, MainActivity::class.java))
						finish()
					}

				}

			}
			.start()
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