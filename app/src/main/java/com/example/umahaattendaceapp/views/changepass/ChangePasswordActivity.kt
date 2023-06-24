package com.example.umahaattendaceapp.views.changepass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Global.getString
import android.util.Log
import com.example.umahaattendaceapp.R
import com.example.umahaattendaceapp.databinding.ActivityChangePasswordBinding
import com.example.umahaattendaceapp.dialog.MyDialog
import com.example.umahaattendaceapp.hawkstorage.HawkStorage
import com.example.umahaattendaceapp.model.ChangePasswordResponse
import com.example.umahaattendaceapp.model.LoginResponse
import com.example.umahaattendaceapp.networking.ApiServices
import com.example.umahaattendaceapp.networking.RetrofitClient
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk.init
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException


class ChangePasswordActivity : AppCompatActivity() {
    companion object{
        private val TAG = ChangePasswordActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityChangePasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()


        OnClick()
    }

    private fun OnClick() {
        binding.btnChangePassword.setOnClickListener {
            finish()
        }
        binding.btnChangePassword.setOnClickListener {
            val oldPass = binding.etOldPassword.text.toString()
            val newPass = binding.etNewPassword.text.toString()
            val confirmNewPass = binding.etConfirmNewPassword.text.toString()
            if (checkValidation(oldPass, newPass, confirmNewPass)){
                changePassToServer(oldPass, newPass, confirmNewPass)
            }
        }

    }

    private fun checkValidation(oldPass: String, newPass: String, confirmNewPass: String): Boolean {
        if (oldPass.isEmpty()){
            binding.etOldPassword.error = getString(R.string.please_field_your_password)
            binding.etOldPassword.requestFocus()
        }else if (newPass.isEmpty()){
            binding.etNewPassword.error = getString(R.string.please_field_your_password)
            binding.etNewPassword.requestFocus()
        }else if (confirmNewPass.isEmpty()){
            binding.etConfirmNewPassword.error = getString(R.string.please_field_your_password)
            binding.etConfirmNewPassword.requestFocus()
        }else if (newPass != confirmNewPass){
            binding.etNewPassword.error = getString(R.string.your_password_didnt_match)
            binding.etNewPassword.requestFocus()
            binding.etConfirmNewPassword.error = getString(R.string.your_password_didnt_match)
            binding.etConfirmNewPassword.requestFocus()
        }else {
            binding.etNewPassword.error = null
            binding.etConfirmNewPassword.error = null
            return true
        }
        return false
    }


    private fun changePassToServer(oldPass: String, newPass: String, confirmNewPass: String) {
        val token = HawkStorage.instance(this).getToken()
        val changePassRequest = ChangePasswordRequest(
            passwordOld = oldPass,
            password = newPass,
            passwordConfirmation = confirmNewPass
        )
        val changePassRequestString = Gson().toJson(changePassRequest)
        MyDialog.showProgressDialog(this)
        ApiServices.getLiveAttendanceServices()
            .changePassword("Bearer $token", changePassRequestString)
            .enqueue(object : Callback<ChangePasswordResponse> {
                override fun onResponse(
                    call: Call<ChangePasswordResponse>,
                    response: Response<ChangePasswordResponse>
                ) {
                    MyDialog.hideDialog()
                    if (response.isSuccessful){
                        MyDialog.dynamicDialog(
                            this@ChangePasswordActivity,
                            getString(R.string.success),
                            getString(R.string.your_password_has_been_update)
                        )
                        Handler(Looper.getMainLooper()).postDelayed({
                            MyDialog.hideDialog()
                            finish()
                        },2000)
                    }else{
                        val errorConverter: Converter<ResponseBody, ChangePasswordResponse> =
                            RetrofitClient
                                .getClient()
                                .responseBodyConverter(
                                    LoginResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                        var errorResponse: ChangePasswordResponse?
                        try {
                            response.errorBody()?.let {
                                errorResponse = errorConverter.convert(it)
                                MyDialog.dynamicDialog(this@ChangePasswordActivity, getString(R.string.failed), errorResponse?.message.toString())
                            }
                        }catch (e: IOException){
                            Log.e(TAG, "Error: ${e.message}")
                        }
                    }
                }

                override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                    MyDialog.hideDialog()
                    MyDialog.dynamicDialog(this@ChangePasswordActivity, getString(R.string.alert), "Error: ${t.message}")
                }

            })
    }

    private fun init() {
        setSupportActionBar(binding.tbChangePassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}

