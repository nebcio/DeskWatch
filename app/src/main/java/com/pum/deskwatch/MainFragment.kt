package com.pum.deskwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.pum.deskwatch.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity: AppCompatActivity = activity as AppCompatActivity

        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val themeSwitch: Switch = binding.themeSwitch
        // listener for switch
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        val modeSwitch: Switch = binding.modeSwitch
        // listener for switch
        modeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                layoutInflater.inflate(R.layout.main_fragment, null).keepScreenOn = true
                //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            } else {
                layoutInflater.inflate(R.layout.main_fragment, null).keepScreenOn = false
                //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
        }

//        view.findViewById<RadioGroup>(R.id.radioButtonTheme).setOnCheckedChangeListener { _, checkedId ->
//            when(checkedId){
//                R.id.radioButtonLight -> {
//                    themeTextView.text = getString(R.string.light_theme)
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                }
//                R.id.radioButtonDark -> {
//                    themeTextView.text = getString(R.string.dark_theme)
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                }
//                R.id.radioButtonDefault -> {
//                    themeTextView.text = getString(R.string.current_theme)
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
//                }
//            }
    }
}