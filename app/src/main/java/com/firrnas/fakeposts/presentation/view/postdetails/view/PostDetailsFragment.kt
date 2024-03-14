package com.firrnas.fakeposts.presentation.view.postdetails.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.firrnas.fakeposts.R
import com.firrnas.fakeposts.databinding.FragmentPostDetailsBinding
import com.firrnas.fakeposts.databinding.PostItemBinding
import com.firrnas.fakeposts.presentation.view.postdetails.viewmodel.PostDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint

class PostDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPostDetailsBinding
    private val postItemViewModel by viewModels<PostDetailsViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val id = PostDetailsFragmentArgs.fromBundle(requireArguments()).id
        postItemViewModel.getDataByUserId(id)
        initObservers()

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {

            postItemViewModel.postDetailsState.collectLatest { postItemState ->
                if (postItemState.loading) {
                    showLoadingState()
                } else {
                    populateUi(postItemState)
                }
            }
            postItemViewModel.errorState.collect {
                showErrorState(it)
            }
        }
    }

    private fun showLoadingState() {
        binding.apply {
            progressBar2.visibility = View.VISIBLE
            tvItemUserId.visibility = View.GONE
            tvItemTitle.visibility = View.GONE
            tvItemBody.visibility = View.GONE
        }
        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
    }

    private fun populateUi(postItemState: PostDetailsState.Display) {
        binding.apply {
            progressBar2.visibility = View.GONE
            tvItemUserId.visibility = View.VISIBLE
            tvItemTitle.visibility = View.VISIBLE
            tvItemBody.visibility = View.VISIBLE
            tvItemUserId.text = postItemState.postUIModel.userId.toString()
            tvItemTitle.text = postItemState.postUIModel.title
            tvItemBody.text = postItemState.postUIModel.body
        }
    }

    private fun showErrorState(errorState: PostDetailsState.Failure) {
        Toast.makeText(requireContext(), errorState.errorMsg, Toast.LENGTH_SHORT).show()
    }
}