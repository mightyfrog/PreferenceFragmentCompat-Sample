package org.mightyfrog.android.preferencefragmentcompatsample

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceCategory
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
                val p = preferenceScreen.getPreference(i)
                if (p is PreferenceCategory) {
                    for (j in 0 until p.preferenceCount) {
                        p.getPreference(j).isIconSpaceReserved = false
                    }
                } else {
                    p.isIconSpaceReserved = false
                }
            }
        }
    }
}