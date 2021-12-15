package com.example.sampe.ui

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<t : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: t
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun bindView(resource: Int) {
        binding = DataBindingUtil.setContentView(this, resource)
    }

    fun showErrorDialog(error: String) {

        AlertDialog.Builder(this).setTitle("Error").setMessage(error)
            .setPositiveButton(
                "OK"
            ) { dialog, which -> }.show()
    }
}