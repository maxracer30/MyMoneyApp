package ru.maxstelmakh.mymoney.presentation.target

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import ru.maxstelmakh.mymoney.databinding.FragmentTargetBinding


class TargetFragment : Fragment() {

    private var _binding: FragmentTargetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTargetBinding.inflate(layoutInflater, container, false)
        return _binding!!.root
    }

    @SuppressLint("CommitPrefEdits", "UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val getPrefs = PreferenceManager.getDefaultSharedPreferences(activity!!)
        val editor = getPrefs.edit()
        val target = getPrefs.getInt("TARGET", 25000)

        with(binding) {
            editTarget.setText(target.toString())
            btnEscape.setOnClickListener {
                findNavController().navigateUp()
            }
            btnSave.setOnClickListener {
                editor
                    .putInt("TARGET", editTarget.text.toString().toInt())
                    .apply()
                findNavController().navigateUp()

            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
