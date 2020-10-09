package org.mightyfrog.android.preferencefragmentcompatsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.mightyfrog.android.preferencefragmentcompatsample.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.apply {
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_settings -> openSettings()
                }

                true
            }
        }
    }

    private fun openSettings() {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToSettingsFragment()
        )
    }
}