package com.example.breakaleg.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakaleg.databinding.FragmentFavouritesBinding
import com.example.breakaleg.presentation.adapter.FavouritesAdapter
import com.example.breakaleg.presentation.viewmodel.MainViewModel
import com.example.domain.common.getResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentFavouritesBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = FavouritesAdapter(viewModel, requireContext(), binding.delete)
        viewModel.getFavoriteNames()
        with(binding) {
            viewModel.names.observe(requireActivity()) { result ->
                result.getResult(
                    success = {
                       adapter.setData(it.data)
                    },
                    error = {
                        adapter.cleanData()
                        Toast.makeText(requireContext(),"The list is empty!", Toast.LENGTH_SHORT).show()
                    }
                )
            }

            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}