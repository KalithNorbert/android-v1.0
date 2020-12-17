package com.example.projectandroid
import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of
import androidx.navigation.fragment.findNavController
import com.example.projectandroid.databinding.GridViewItemBinding
import com.example.projectandroid.databinding.RestaurantsFragmentBinding

class RestaurantsFragment : Fragment() {


    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProvider(this).get(RestaurantsViewModel::class.java)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = RestaurantsFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.photosGrid.adapter = PhotoAdapter(PhotoAdapter.OnClickListener {
            viewModel.displayRestaurantDetails(it)
        })

        viewModel.navigateSelectedRestaurant.observe(this, Observer {
            if ( null != it){
                this.findNavController().navigate(RestaurantsFragmentDirections.actionShowDatas(it))
                viewModel.displayRestaurantDetailsComplete()
            }

        })

        setHasOptionsMenu(true)

        return binding.root

    }



}