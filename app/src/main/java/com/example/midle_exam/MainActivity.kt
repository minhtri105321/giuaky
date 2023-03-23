package com.example.midle_exam

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.midle_exam.database.ConnectDB
import com.example.midle_exam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var db:ConnectDB? = null
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // connect database
        db = ConnectDB(this)
        db?.openDatabase()
        // data helper
        var helper = ConnectDB(applicationContext)
        var data = helper.readableDatabase

        // btn register
        binding.btnRegister.setOnClickListener {
            evenrAdd()
        }
    }

    private fun evenrAdd() {

        // data helper
        var helper = ConnectDB(applicationContext)
        var data = helper.readableDatabase

        // get data
        val mail = findViewById<EditText>(R.id.edtEmail)
        val user = findViewById<EditText>(R.id.edtUser)
        val pass = findViewById<EditText>(R.id.edtPass)
        val passAgain = findViewById<EditText>(R.id.edtAgainPass)



        var count : Int =0
        if (pass.text.toString()!=passAgain.text.toString()){
            passAgain.error = "Mật khẩu không trùng khớp!"
            count++
        }
        var rsUser = data.rawQuery("Select * from users where name = '${user.text.toString()}'",null)
        if (rsUser.count>0){
            user.error = "Tên đăng nhập đã tồn tại!"
            count++
        }

        if (mail.text.toString().isEmpty()){
            mail.error = "Vui lòng điền vào trường này!"
            count++
        }
        if (user.text.toString().isEmpty()){
            user.error = "Vui lòng điền vào trường này!"
            count++
        }
        if (pass.text.toString().isEmpty()){
            mail.error = "Vui lòng điền vào trường này!"
            count++
        }
        if (passAgain.text.toString().isEmpty()){
            mail.error = "Vui lòng điền vào trường này!"
            count++
        }

        if ( count==0){
            var insert = ContentValues()
            insert.put("mail",mail.text.toString())
            insert.put("name",user.text.toString())
            insert.put("pass",pass.text.toString())

            data.insert("users",null,insert)
            Toast.makeText(this,"Thêm thành công!",Toast.LENGTH_SHORT).show()
        }













    }
}