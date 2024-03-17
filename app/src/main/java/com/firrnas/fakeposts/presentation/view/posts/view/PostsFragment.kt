package com.firrnas.fakeposts.presentation.view.posts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.firrnas.fakeposts.R
import com.firrnas.fakeposts.databinding.FragmentPostsBinding
import com.firrnas.fakeposts.presentation.view.posts.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint

class PostsFragment : Fragment() {


    private lateinit var binding: FragmentPostsBinding
    private val postViewModel by viewModels<PostViewModel>()
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
    }

    private fun initViews() {
        postsAdapter = PostsAdapter {
            val action=PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.recyclerView.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            postViewModel.getPost()
            postViewModel.userState.collectLatest { userState ->
                if (userState.loading) {
                    showLoadingState()
                } else {
                    populateUi(userState)
                }
            }
            postViewModel.errorState.collect {
                showErrorState(it)
            }
        }
    }

    private fun showLoadingState() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility=View.GONE
        }
        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
    }

    private fun populateUi(postState: PostState.Display) {
        Log.d("PostsFragment", "PostState: $postState")

        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility=View.VISIBLE
        postsAdapter.submitList(postState.postUiModel)
    }

    private fun showErrorState(errorState: PostState.Failure) {
        Toast.makeText(requireContext(), errorState.errorMsg, Toast.LENGTH_SHORT).show()
    }
}