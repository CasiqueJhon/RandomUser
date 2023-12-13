package com.example.randomusertest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomusertest.R
import com.example.randomusertest.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var binding: FragmentUserListBinding? = null

    private val viewModel: UserListViewModel by viewModels()

    private val adapter = UserAdapter(emptyList())

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
    }

}