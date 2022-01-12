package com.rbernard.project3weatherapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.Volley
import com.rbernard.project3weatherapp.databinding.MainFragmentBinding
import com.squareup.picasso.Picasso

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }//end onCreateView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //create a default city for when app opens
        setCity("Detroit")

    }//end onActivityCreated

    //function to get the City name  from user input
    fun setCity(name: String){

        //set city text to match user input
        binding.city.text = name

        //create two request queues (current weather, forecast weather)
        val queue1 = Volley.newRequestQueue(context)

        //send request to ViewModel (where processing network call)
        viewModel.current(name,queue1)

        //create observers to display the values and get the value
        // from each mutable live data variables
        val tempObserver = Observer<String>{temp-> binding.temp.text=temp}
        viewModel.getTemp().observe(viewLifecycleOwner,tempObserver)

        val dateObserver = Observer<String>{date-> binding.date.text=date}
        viewModel.getDate().observe(viewLifecycleOwner,dateObserver)

        val descriptionObserver = Observer<String>{description-> binding.description.text=description}
        viewModel.getDescription().observe(viewLifecycleOwner,descriptionObserver)

        val iconObserver = Observer<String>{icon->
            Picasso.with(context)
                .load(icon).resize(150,150).into(binding.icon)
        }
        viewModel.getIcon().observe(viewLifecycleOwner,iconObserver)

        setForecast()
    }//end setCity*/

        fun setForecast(){

            val queue2 = Volley.newRequestQueue(context)
            viewModel.forecast(queue2)

            //forecast weather (arrayList for RecyclerView)
            //getting arrayList from viewModel and send it to adapter
            val listObserver = Observer<ArrayList<Items>>{list ->
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
                binding.recyclerView.adapter = RecyclerAdapter(list)
            }
            viewModel.getList().observe(viewLifecycleOwner,listObserver)
        }//end setForecast


}//end MainFragment