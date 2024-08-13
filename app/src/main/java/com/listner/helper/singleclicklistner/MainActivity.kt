package com.listner.helper.singleclicklistner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.listner.helper.singleclicklistner.databinding.ActivityMainBinding
import com.listner.helper.singleclicklistner.extensions.Executions.setOnOneClickListener

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val liveCount = MutableLiveData(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            liveCount.observe(this@MainActivity) {
                tvCount.text = it.toString()
            }
            btnAdd.setOnOneClickListener /*Next click will valid after 1000 milliseconds*/{
                val count = liveCount.value ?: 0
                liveCount.postValue(count + 1)
            }

            btnMinus.setOnOneClickListener /*Next click will valid after 1000 milliseconds*/{
                val count = liveCount.value ?: 0
                if (count > 0)
                    liveCount.postValue(count - 1)
            }
        }
    }
}