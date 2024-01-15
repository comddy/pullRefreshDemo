package com.example.testapp.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.base.show
import com.example.testapp.databinding.ActivitySecondBinding
import kotlin.random.Random

class SecondActivity : Activity() {
    private val TAG = "SecondActivity"
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView
        val data = arrayListOf("1", "2", "3")
        val adapter = MyAdapter(data)
        val random = Random(42)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // 记录当前滑动状态
                if (newState == RecyclerView.SCROLL_STATE_IDLE) { //当前状态为停止滑动
                    if (!recyclerView.canScrollVertically(-1)) { // 到达顶部
                        data.add(0, random.nextInt().toString())
                        data.add(0, random.nextInt().toString())
                        adapter.notifyItemRangeInserted(0, 2)
                        "到顶了".show(recyclerView.context)
                        Log.d(TAG, "到顶了")
                    }
                }
            }
        })
    }

    inner class MyAdapter(val list: List<String>) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

        inner class MyHolder(view: View): RecyclerView.ViewHolder(view) {
            val textView: TextView
            init {
                textView = view.findViewById(R.id.textView)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
            val view = View.inflate(this@SecondActivity, R.layout.item_list, null)
            return MyHolder(view)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.textView.text = list[position]
        }
    }
}