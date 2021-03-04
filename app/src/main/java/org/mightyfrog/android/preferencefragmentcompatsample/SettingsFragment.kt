package org.mightyfrog.android.preferencefragmentcompatsample

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import com.google.android.material.appbar.MaterialToolbar

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.prefs, rootKey)
    }

    /**
     * https://issuetracker.google.com/issues/111907042
     */
    override fun setPreferenceScreen(preferenceScreen: PreferenceScreen?) {
        super.setPreferenceScreen(preferenceScreen)

        if (preferenceScreen != null) {
            for (i in 0 until preferenceScreen.preferenceCount) {
                preferenceScreen.getPreference(i).isIconSpaceReserved = false

                // need to do the same to PreferenceCategory if there is any
//                val pc = preferenceScreen.getPreference(i) as PreferenceCategory
//                for (j in 0 until pc.preferenceCount) {
//                    pc.getPreference(j).isIconSpaceReserved = false
//                }
//                pc.isIconSpaceReserved = false
            }
        }
    }
}