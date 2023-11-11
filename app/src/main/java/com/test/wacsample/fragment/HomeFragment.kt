package com.test.wacsample.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.wacsample.adapter.BannerAdapter
import com.test.wacsample.adapter.CategoriesshowAdapter
import com.test.wacsample.adapter.ProductsAdapter
import com.test.wacsample.databinding.FragmentHomeBinding
import com.test.wacsample.model.Product
import com.test.wacsample.viewmodel.HomeViewModel


class HomeFragment : Fragment(), ProductsAdapter.Listener {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var categoryAdapter: CategoriesshowAdapter
    private lateinit var bannerAdapter: BannerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val layoutManager =  GridLayoutManager(activity, 1, RecyclerView.HORIZONTAL, false)// oluyorsa layout managerları birleştir.
        val layoutManager2 =  GridLayoutManager(activity, 1, RecyclerView.HORIZONTAL, false)// oluyorsa layout managerları birleştir.
        val layoutManagerBanner =  GridLayoutManager(activity, 1, RecyclerView.HORIZONTAL, false)// oluyorsa layout managerları birleştir.
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerViewcategory.layoutManager = layoutManager2
        binding.recyclerViewbanner.layoutManager = layoutManagerBanner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.getData(requireContext())
        viewModel.products.observe(viewLifecycleOwner, androidx.lifecycle.Observer { products ->
            products.let {
                productsAdapter = products?.let { it1 ->
                    ProductsAdapter(it1, requireContext() // TODO Fragment HomeFragment not attached to a context.
                    )
                }!!
                binding.recyclerView.adapter = productsAdapter
            }

        })

        ///////////category/////////
        viewModel.category.observe(viewLifecycleOwner, androidx.lifecycle.Observer { category ->
            category.let {
                categoryAdapter = category?.let { it2 ->
                    CategoriesshowAdapter(it2, requireContext() // TODO Fragment HomeFragment not attached to a context.
                    )
                }!!
                binding.recyclerViewcategory.adapter = categoryAdapter
            }

        })

        ///////////category/////////
        viewModel.banner.observe(viewLifecycleOwner, androidx.lifecycle.Observer { banner ->
            banner.let {
                bannerAdapter = banner?.let { it3 ->
                    BannerAdapter(it3, requireContext() // TODO Fragment HomeFragment not attached to a context.
                    )
                }!!
                binding.recyclerViewbanner.adapter = bannerAdapter
            }

        })



    }


    override fun onItemClick(products: Product) {
        Toast.makeText(activity, "item is clicked", Toast.LENGTH_SHORT).show()
    }





}