package uz.orign.fourpicture.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import uz.orign.fourpicture.*
import uz.orign.fourpicture.databinding.FragmentHomeBinding
import uz.orign.fourpicture.ui.global.base.BaseFragment

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _bn: FragmentHomeBinding? = null
    private val bn get() = _bn ?: throw NullPointerException("cannot inflate")

    private val answerButtons = ArrayList<AppCompatButton>()
    private val variantButtons = ArrayList<AppCompatButton>()

    private val viewModel: GameViewModel by viewModels()
    override fun setUpUI() {
        TODO("Not yet implemented")
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _bn = FragmentHomeBinding.bind(view)
        loadViews()
        viewModel.hideAnswerLiveData.observe(this, hideAnswerObserver)
        viewModel.showAnswerLiveData.observe(this, showAnswerObserver)
        viewModel.hideVariantLiveData.observe(this, hideVariantObserver)
        viewModel.showVariantLiveData.observe(this, showVariantObserver)
        viewModel.questionLiveData.observe(viewLifecycleOwner, questionObserver)
        viewModel.variantsLiveData.observe(viewLifecycleOwner, variantObserver)
        viewModel.writeAnswerLiveData.observe(viewLifecycleOwner, writeAnswerObserver)
    }


    private val hideAnswerObserver = Observer<Int> { answerButtons[it].gone() }
    private val showAnswerObserver = Observer<Int> { answerButtons[it].visible() }
    private val hideVariantObserver = Observer<Int> { variantButtons[it].invisible() }
    private val showVariantObserver = Observer<Int> { variantButtons[it].visible() }
    private val questionObserver = Observer<Int> { bn.ivQuestion.setImageResource(it) }
    private val variantObserver = Observer<String> { variantButtons.forEachIndexed { index, appCompatButton -> appCompatButton.text = it[index].toString() } }
    private val writeAnswerObserver = Observer<Pair<Int, String>> { answerButtons[it.first].text = it.second }

    private fun loadViews() {
        bn.apply {
            loadViewsBtn(layoutAnswers, answerButtons, viewModel::clickAnswer)
            loadViewsBtn(layoutVariant1, variantButtons, viewModel::clickVariant)
            loadViewsBtn(layoutVariant2, variantButtons, viewModel::clickVariant)
        }

    }

    private fun loadViewsBtn(layout: LinearLayoutCompat, ls: MutableList<AppCompatButton>, block: (Int) -> Unit) {
        for (i in 0 until layout.childCount) {
            val button = layout.getChildAt(i)
            if (button is AppCompatButton) {
                button.setOnClickListener { block(i) }
                ls.add(button)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startGame()
    }

}