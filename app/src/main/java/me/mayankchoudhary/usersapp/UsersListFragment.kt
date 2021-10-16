package me.mayankchoudhary.usersapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import me.mayankchoudhary.usersapp.adapter.UsersListAdapter
import me.mayankchoudhary.usersapp.databinding.FragmentUserListBinding
import me.mayankchoudhary.usersapp.viewmodel.UserFactory
import me.mayankchoudhary.usersapp.viewmodel.UsersListViewModel

class UsersListFragment : Fragment() {

    private val viewModel: UsersListViewModel by activityViewModels {
        UserFactory(
            (activity?.application as UserApplication).database
                .userDao()
        )
    }
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    val userAdapter = UsersListAdapter()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_news_list, container, false)
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.apply {
                adapter = userAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            viewModel.photos.observe(viewLifecycleOwner) {
                userAdapter.submitList(it)
            }
            viewModel.eventNetworkError.observe(viewLifecycleOwner) {
                if (it == true) {
                    error.visibility = View.VISIBLE
                } else {
                    error.visibility = View.GONE
                }
            }
//            swipeRefresh.setOnRefreshListener { this}
            swipeRefresh.setOnRefreshListener {
                viewModel.getPhotos()
                swipeRefresh.isRefreshing = false
            }
        }
    }
}