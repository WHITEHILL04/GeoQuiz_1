package kz.kaspi.geoquiz_1

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
	private lateinit var trueButton : Button
	private lateinit var falseButton : Button
	private lateinit var nextButton: Button
	private lateinit var prevButton: Button
	private lateinit var  questionTextView: TextView

	private var currentIndex = 0

	private val questionsBank = listOf(
		Questions(R.string.question_australia, true),
		Questions(R.string.question_ocean, true),
		Questions(R.string.question_mideast, false),
		Questions(R.string.question_africa, false),
		Questions(R.string.question_americas, true),
		Questions(R.string.question_asia, true))




	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		trueButton = findViewById(R.id.true_btn)
		falseButton = findViewById(R.id.false_btn)
		nextButton = findViewById(R.id.next_btn)
		prevButton = findViewById(R.id.prev_button)
		questionTextView = findViewById(R.id.question_text_view)

		trueButton.setOnClickListener { _view: View ->
			checkAnswer(true)
		}

		falseButton.setOnClickListener{ _view: View ->
			checkAnswer(false)
		}

		nextButton.setOnClickListener {
			currentIndex = (currentIndex + 1) % questionsBank.size
			updateQuestion()
		}

		prevButton.setOnClickListener {
			currentIndex = (questionsBank.size + (currentIndex - 1)) % questionsBank.size
			updateQuestion()
		}

		questionTextView.setOnClickListener {
			currentIndex = (currentIndex + 1) % questionsBank.size
			updateQuestion()
		}
		updateQuestion()
	}



	private fun updateQuestion(){
		val questionTextResId = questionsBank[currentIndex].textResId
		questionTextView.setText(questionTextResId)
	}


	private fun checkAnswer(userAnswer: Boolean){
		val correctAnswer = questionsBank[currentIndex].answer

		val messageResId = if (userAnswer == correctAnswer){
			R.string.correct_toast
		} else{
			R.string.incorrect_toast
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
	}
}
