package com.example.projectandroid
import android.annotation.SuppressLint
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders.of
import androidx.navigation.fragment.findNavController
import com.example.projectandroid.databinding.GridViewItemBinding
import com.example.projectandroid.databinding.RestaurantsFragmentBinding
import com.example.projectandroid.datas.RestaurantDataFragmentArgs
import com.example.projectandroid.datas.RestaurantDataViewMode
import com.example.projectandroid.datas.RestaurantDataViewModelFactory
import com.example.projectandroid.network.RestaurantsApi
import kotlinx.android.synthetic.main.restaurants_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantsFragment : Fragment() {


    //berakja a ViewModelbe a RestaurantViewModelt
    private val viewModel: RestaurantsViewModel by lazy {
        ViewModelProvider(this).get(RestaurantsViewModel::class.java)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //ráteszi a fragmentre a kinézetet
        val binding = RestaurantsFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        //a photoGrid adapter onclickListener fgv átadja azt a Restaurantoto amire ráklikkeltem
        //ezt a displayRestaurantDetails megkapja
        binding.photosGrid.adapter = PhotoAdapter(PhotoAdapter.OnClickListener {
            viewModel.displayRestaurantDetails(it)//it restaurant típúsu lesz az lessz amire ráklikeeltem
        })


        ///observe figyelem a változást
        //benavigál a kiválasztott restaurantba
        //ha az eventnek vége van akkor  -null-  értéket ad ezért az observe nem ellenörzi mig nem kap új értéket
        viewModel.navigateSelectedRestaurant.observe(this, Observer {
            if ( null != it){
                this.findNavController().navigate(RestaurantsFragmentDirections.actionShowDatas(it))
                viewModel.displayRestaurantDetailsComplete()
            }

        })



/*
        val job = Job()
        val coroutineScope = CoroutineScope(job + Dispatchers.Main)

        coroutineScope.launch {
            val getInformation = RestaurantsApi.retrofitService.getProperties()




            if (getInformation.page == 1)
            {
                btn_prev.text = ""
                btn_prev.setOnClickListener(){
                    Toast.makeText(context, "You are on the first page", Toast.LENGTH_SHORT).show()
                }

                Toast.makeText(context, getInformation.page.toString(), Toast.LENGTH_SHORT).show()
                btn_next.setOnClickListener(){
                    getInformation.page ++
                    Toast.makeText(context, getInformation.page.toString(), Toast.LENGTH_SHORT).show()

                    //reload fragment
                    reload()


                }
                Toast.makeText(context, getInformation.page.toString(), Toast.LENGTH_SHORT).show()
            }
            if (getInformation.page == 2)
            {
                btn_next.setOnClickListener(){
                    getInformation.page ++
                    reload()
                }
                Toast.makeText(context, "second", Toast.LENGTH_SHORT).show()
            }
            if (getInformation.page == 3)
            {
                Toast.makeText(context, "third", Toast.LENGTH_SHORT).show()
            }


        }
*/


        //a navigation bárok miatt szükséges
        setHasOptionsMenu(true)

        //az inflatert téríti vissza
        return binding.root
    }

    private fun reload() {
        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
        Toast.makeText(context, "reload", Toast.LENGTH_SHORT).show()
        ft.detach(this).attach(this).commit()
    }


}