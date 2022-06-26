package uz.orign.fourpicture.ui.menu

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import uz.orign.fourpicture.ui.global.base.BaseFragment
import uz.orign.fourpicture.R
import uz.orign.fourpicture.databinding.FragmentMenuBinding

class MenuFragment : BaseFragment(R.layout.fragment_menu) {

    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    private var _bn: FragmentMenuBinding? = null
    private val bn get() = _bn ?: throw NullPointerException("cannot inflate")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _bn = FragmentMenuBinding.bind(view)
        setUpUI()
    }

    override fun setUpUI() {
        bn.apply {
            btnStart.setOnClickListener { navController.navigate(R.id.action_menuFragment_to_homeFragment) }
        }
    }

}