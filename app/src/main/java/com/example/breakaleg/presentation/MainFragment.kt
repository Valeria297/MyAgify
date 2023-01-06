package com.example.breakaleg.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.breakaleg.R
import com.example.breakaleg.databinding.FragmentMainBinding
import com.example.breakaleg.presentation.viewmodel.MainViewModel
import com.example.domain.common.getResult
import com.example.domain.entities.NameEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentMainBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            name.setOnEditorActionListener { _, actionId, _ ->
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    viewModel.getAgeByName(name.text.toString())
                    greatings.visibility = View.GONE
                    age.visibility = View.VISIBLE
                    toFavourites.visibility = View.VISIBLE
                    share.visibility = View.VISIBLE
                    handled = true
                }

                requireContext().hideKeyboard(name)
                handled
            }

            viewModel.age.observe(requireActivity()) { result ->
                result.getResult(
                    success = {
                        age.text = it.data.toString()
                    },
                    error = {
                        Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                    }
                )
            }

            toFavourites.setOnClickListener {
                viewModel.addFavoriteName(NameEntity(name = name.text.toString()))
                Toast.makeText(
                    requireContext(),
                    "The data was added to favourites.",
                    Toast.LENGTH_SHORT
                ).show()
                cleanData()
            }

            share.setOnClickListener {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.sharing) + " " + age.text.toString()
                            + "\n" + getString(R.string.sharing2)
                );
                startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
                cleanData()
            }
        }
    }

    private fun cleanData() {
        with(binding) {
            name.text.clear()
            greatings.visibility = View.VISIBLE
            age.visibility = View.GONE
            toFavourites.visibility = View.GONE
            share.visibility = View.GONE
        }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}