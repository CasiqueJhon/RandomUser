package com.example.randomusertest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomusertest.R
import com.example.randomusertest.databinding.FragmentUserListBinding
import com.example.randomusertest.domain.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var binding: FragmentUserListBinding? = null

    private val viewModel: UserListViewModel by viewModels()

    private val adapter = UserAdapter(emptyList()) { user ->
        onUserClick(user)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            UserListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.userList?.layoutManager = LinearLayoutManager(context)
        binding?.userList?.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner) { users ->
            adapter.updateData(users)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        binding?.userList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.nextPage()
                }
            }
        })
    }

    private fun onUserClick(user: User) {
        val bundle = Bundle()
        bundle.putParcelable("user", user)

        val fragment = UserDetailFragment.newInstance()
        fragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.userList_fragment_container, fragment)
            .addToBackStack("UserListFragment")
            .commit()
    }
}