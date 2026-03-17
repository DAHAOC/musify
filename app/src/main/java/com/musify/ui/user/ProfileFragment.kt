package com.musify.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.musify.R
import com.musify.databinding.FragmentProfileBinding
import com.musify.ui.common.HorizontalSpaceItemDecoration

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tracksAdapter = UserTracksAdapter(
            emptyList(), { item ->
                Toast.makeText(requireContext(), "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
            })
        binding.tracksList.adapter = tracksAdapter
        binding.tracksList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val trackSpacing = resources.getDimensionPixelSize(R.dimen.item_margin_medium)
        binding.tracksList.addItemDecoration(HorizontalSpaceItemDecoration(trackSpacing))

        viewModel.tracks.observe(viewLifecycleOwner) { trackResults ->
            tracksAdapter.updateList(trackResults)
        }

        val userImage = "https://cdn.pfps.gg/pfps/1957-patrick-star-profile-photo.png"
        Glide.with(requireContext()).load(userImage).centerCrop()
            .placeholder(R.drawable.ic_person)
            .transform(RoundedCorners(resources.getDimensionPixelSize(R.dimen.radius_medium)))
            .into(binding.userIcon)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.editButton.setOnClickListener {
            // Handle edit
            Toast.makeText(requireContext(), "Edit profile", Toast.LENGTH_SHORT).show()
        }

        binding.editButton.setOnClickListener {
            // Handle edit
            Toast.makeText(requireContext(), "Edit profile", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}