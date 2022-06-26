package uz.orign.fourpicture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.orign.fourpicture.util.constants.CONSTANTS

class GameViewModel : ViewModel() {
    private val repository = GameRepository()
    private var currentQuestion = 0
    private lateinit var questionData : QuestionData
    private lateinit var answerState:Array<Boolean>
    private lateinit var variantState:Array<Boolean>
    private lateinit var answerText:Array<String>


    private val _hideVariantLiveData = MutableLiveData<Int>()
    val hideVariantLiveData: LiveData<Int> = _hideVariantLiveData

    private val _questionLiveData = MutableLiveData<Int>()
    val questionLiveData: LiveData<Int> = _questionLiveData

    private val _showAnswerLiveData = MutableLiveData<Int>()
    val showAnswerLiveData: LiveData<Int> = _showAnswerLiveData

    private val _showVariantLiveData = MutableLiveData<Int>()
    val showVariantLiveData: LiveData<Int> = _showVariantLiveData

    private val _hideAnswerLiveData = MutableLiveData<Int>()
    val hideAnswerLiveData: LiveData<Int> = _hideAnswerLiveData

    private val _variantsLiveData = MutableLiveData<String>()
    val variantsLiveData: LiveData<String> = _variantsLiveData

    private val _writeAnswerLiveData = MutableLiveData<Pair<Int, String>>()
    val writeAnswerLiveData: LiveData<Pair<Int, String>> = _writeAnswerLiveData

    init {
        loadQuestion()
    }

    fun clickVariant(index: Int) {
        val answerIndex = findFirstEmptyAnswer()
        if (answerIndex == -1 ) return
        _hideVariantLiveData.value = index
        variantState[index] = false
        val answer = questionData.variant[index].toString()
        _writeAnswerLiveData.value = answerIndex to answer
        answerText[answerIndex] = answer
        answerState[answerIndex] = true
    }

    fun clickAnswer(index: Int) {
        val answer = answerText[index]
        val indexVariant = findFirstVariantByText(answer)
        if (indexVariant == -1 ) return

        _showVariantLiveData.value = indexVariant
        variantState[indexVariant]  = true
        answerState[index] = false
        answerText[index] = ""
        _writeAnswerLiveData.value = index to ""
    }

    private fun loadQuestion() {
        questionData = repository.getQuestionByIndex(currentQuestion)

        _questionLiveData.value = questionData.question
        _variantsLiveData.value = questionData.variant
        val answerLength = questionData.answer.length
        answerState = Array(answerLength, init = {false})
        answerText = Array(answerLength, init = {""})
       variantState = Array(questionData.variant.length ){true}
        for (i in 0 until CONSTANTS.MAX_ANSWER){
            if (i < answerLength){
                _showAnswerLiveData.value = i
            }else{
                _hideAnswerLiveData.value = i
            }
        }

    }

    private fun checkAnswer(){

    }

    private fun findFirstEmptyAnswer()= answerState.indexOfFirst { !it }

    private fun findFirstVariantByText(text:String):Int =  questionData.variant.indexOfFirst { it.toString() ==  text }

    fun startGame(){
        loadQuestion()
    }
}