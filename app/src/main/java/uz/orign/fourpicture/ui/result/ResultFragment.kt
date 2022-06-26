package uz.orign.fourpicture.ui.result

import android.os.Bundle
import android.view.View
import uz.orign.fourpicture.R
import uz.orign.fourpicture.databinding.FragmentResultBinding
import uz.orign.fourpicture.ui.global.base.BaseFragment

class ResultFragment : BaseFragment(R.layout.fragment_result) {

    private var _bn: FragmentResultBinding? = null
    private val bn get() = _bn ?: throw NullPointerException("cannot inflate")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _bn = FragmentResultBinding.bind(view)
        setUpUI()
    }

    override fun setUpUI() {
        bn.apply {

        }
    }


}