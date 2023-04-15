package com.hm.budgettraker.Activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.hm.budgettraker.Fragments.AddFragment
import com.hm.budgettraker.Fragments.HomeFragment
import com.hm.budgettraker.R
import com.hm.budgettraker.databinding.ActivityMainBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {

                when(newIndex) {
                    0-> loadFragment(HomeFragment())
                    1-> loadFragment(AddFragment())
                    else->loadFragment(HomeFragment())

                }

            }
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {

            }
        })


    }

    private fun loadFragment(fragment:Fragment) {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragFrame, fragment)
        transaction.commit()
    }
}