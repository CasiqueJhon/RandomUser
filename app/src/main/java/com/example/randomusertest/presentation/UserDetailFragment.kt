package com.example.randomusertest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.randomusertest.R
import com.example.randomusertest.databinding.FragmentUserDetailBinding
import com.example.randomusertest.domain.User
import com.google.android.gms.maps.MapsInitializer
import java.text.SimpleDateFormat
import java.util.Locale

class UserDetailFragment : Fragment() {

    private var binding: FragmentUserDetailBinding? = null

    companion object {
        @JvmStatic
        fun newInstance() =
            UserDetailFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getParcelable<User>("user")
        if (user != null) {
            binding?.userImagePicture?.let {
                Glide.with(binding!!.userImagePicture.context)
                    .load(user.picture.large)
                    .circleCrop()
                    .into(it)
            }
            binding?.userDetailSimpleName?.text = "${user.name.first} ${user.name.last}"
            binding?.profileTitle?.text = getString(R.string.user_full_name)
            binding?.userFullName?.text = "${user.name.title} ${user.name.first} ${user.name.last}"
            binding?.emailTitle?.text = getString(R.string.user_email)
            binding?.userEmail?.text = user.email
            binding?.genderTitle?.text = getString(R.string.user_gender)
            binding?.userGender?.text = user.gender
            binding?.registerDateTitle?.text = getString(R.string.user_registered_date)
            dateFormat(user)
            binding?.phoneTitle?.text = getString(R.string.user_phone_number)
            binding?.userPhone?.text = user.phone
            binding?.backArrow?.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun dateFormat(user: User) {
        val dateString = user.registered.date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        if (date != null) {
            val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            binding?.userRegisterDate?.text = outputFormat.format(date)
        }

    }
}