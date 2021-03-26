package com.crash.kit

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_crash_log.*
import kotlinx.android.synthetic.main.activity_crash_log_list_item.view.*
import java.io.File

/**
 * crash日志页面
 */
class CrashLogActivity : AppCompatActivity() {


    private var crashLogAdapter: CrashLogAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash_log)

        val crashFiles = CrashKitManager.crashFiles()

        val list = ArrayList<File>()
        for (file in crashFiles) {
            list.add(file)
        }
        recycler_view.layoutManager = LinearLayoutManager(this)

        crashLogAdapter = CrashLogAdapter(list)
        recycler_view.adapter = crashLogAdapter
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        decoration.setDrawable(
            ContextCompat.getDrawable(
                this,
                R.drawable.shape_divider
            )!!
        )
        recycler_view.addItemDecoration(decoration)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_crash, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> {
                CrashKitManager.deleteFiles()
                crashLogAdapter?.clearData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class CrashLogAdapter(val crashFilesList: ArrayList<File>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun getItemCount(): Int {
            return crashFilesList.size
        }

        //样式
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return object : RecyclerView.ViewHolder(
                layoutInflater.inflate(
                    R.layout.activity_crash_log_list_item,
                    parent,
                    false
                )
            ) {}
        }

        //绑定数据
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val file = crashFilesList.get(position)
            holder.itemView.file_title.text = file.name
            holder.itemView.file_share.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra("subject", "")
                intent.putExtra("body", "")

                //当前设备>7.0
                val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    FileProvider.getUriForFile(
                        this@CrashLogActivity,
                        "${packageName}.fileProvider",
                        file
                    )
                else {
                    Uri.fromFile(file)
                }

                intent.putExtra(Intent.EXTRA_STREAM, uri)//添加文件
                if (file.name.endsWith(".txt")) {
                    intent.type = "text/plain"//纯文本
                } else {
                    intent.type = "application/octet-stream" //二进制文件流
                }
                startActivity(Intent.createChooser(intent, "分享Crash 日志文件"))
            }
        }

        fun clearData() {
            crashFilesList.clear()
            notifyDataSetChanged()
        }

    }
}