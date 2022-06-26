package uz.orign.fourpicture

class GameRepository {
    private val data = ArrayList<QuestionData>()

    init {
        loadData()
    }

    private fun loadData() {
        data.add(QuestionData("olma", R.drawable.apple, "asdfghjolnmawtrx"))
        data.add(QuestionData("banan", R.drawable.banana, "asnfghbnlnmawtrx"))
    }

    fun getQuestionByIndex(index:Int) = data[index]
}