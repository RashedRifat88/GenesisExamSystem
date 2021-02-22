package com.egsystem.genesisexamsystem.credential;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.afollestad.materialdialogs.MaterialDialog;
import com.egsystem.genesisexamsystem.HomeActivity;
import com.egsystem.genesisexamsystem.R;
import com.egsystem.genesisexamsystem.SplashActivity;
import com.egsystem.genesisexamsystem.data.shared_pref.SharedData;
import com.egsystem.genesisexamsystem.model.ErrorResponseGeneric;
import com.egsystem.genesisexamsystem.model.LoginModel;
import com.egsystem.genesisexamsystem.retrofit.RetrofitApiClient;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    Button btnLogin, btnRegister;
    TextView tvForgotPass;
    TextView etUserId, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        predefinedTextSet();
    }

    private void predefinedTextSet() {
        String s_bmdc_no = SharedData.getBMDC_NO(this);
        String s_password = SharedData.getPASSWORD(this);

        if (s_bmdc_no != null) {
            etUserId.setText(s_bmdc_no);
        }

        if (s_password != null) {
            etPassword.setText(s_password);
        }

    }

    private void init() {
        progressDialog = new ProgressDialog(this);

        etUserId = findViewById(R.id.etUserId);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        tvForgotPass = findViewById(R.id.tvForgotPass);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }


    private void loginAccount() {

        String bmdc_no = etUserId.getText().toString();
        String password = etPassword.getText().toString();
//        String phone = "01533440316";
//        String password = "123456";

        if (TextUtils.isEmpty(bmdc_no)) {
            Toast.makeText(this, "Plz write your bmdc number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Plz fill up your password", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setTitle("Login Account");
            progressDialog.setMessage("Plz wait, while we are checking the credentials...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            loginApiCall(bmdc_no, password);
        }

    }


    @SuppressLint("CheckResult")
    public void loginApiCall(String bmdc_no, String password) {

//        custPrograssbar.prograssCreate(SearchActivity1.this);

//        String token = SharedData.getTOKEN(this);
//        String authorization = "Bearer" + " " + token;
        String accept = "application/json";

        RetrofitApiClient.getApiInterface().login(accept, bmdc_no, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());


                            progressDialog.dismiss();

                            if (response.code() == 403) {
                                Log.d("tag11111", " 403 is defined ");
                            }


                            if (response.code() == 403) {

//                                new MaterialDialog.Builder(LoginActivity.this)
//                                        .title("Login Status")
//                                        .content(loginMessage)
//                                        .positiveText("")
//                                        .negativeText("Ok")
//                                        .show();
                            }

                            if (response.isSuccessful()) {

                                LoginModel loginModel = response.body();
                                Log.d("tag11111", " loginModel: " + loginModel);
                                String loginMessage = loginModel.getMessage();
                                Log.d("tag11111", " loginMessage: " + loginMessage);

//                                custPrograssbar.closePrograssBar();


                                response.body();
                                Log.d("tag11111", " response.body(): " + response.body());

//                                LoginModel loginModel = response.body();

                                LoginModel.Data loginData = loginModel.getData();
                                String loginToken = loginModel.getToken();
                                String loginMessage2 = loginModel.getMessage();
                                String user_name = loginData.getName();
                                Log.d("tag11111", " loginToken: " + loginToken);
                                Log.d("tag11111", " loginMessage2: " + loginMessage2);

                                SharedData.saveTOKEN(this, loginToken);
                                SharedData.saveUSER_NAME(this, user_name);
                                SharedData.saveBMDC_NO(this, bmdc_no);
                                SharedData.savePASSWORD(this, password);

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();


                            } else {
//                                response.errorBody().string(); // do something with that
                                Gson gson = new Gson();
                                ErrorResponseGeneric errorResponse = gson.fromJson(
                                        response.errorBody().string(),
                                        ErrorResponseGeneric.class);

//                                List<String> errorList = errorResponse.getValidationError();
                                ErrorResponseGeneric.Errors error = errorResponse.getErrors();
                                List<String> bmdc_error_list = error.getBmdcNo();
                                List<String> passwd_error_list = error.getPassword();

//                                String errorNames = "1. ";
//                                for (int i = 0; i < errorList.size(); i++) {
//                                    int j = i + 2;
//                                    errorNames = errorNames
//                                            + errorList.get(i);
//                                    if (i != errorList.size() - 1) {
//                                        errorNames = errorNames + "\n\n" + j + ". ";
//                                    }
//
//                                }

                                String errorNames = "";
                                for (int i = 0; i < bmdc_error_list.size(); i++) {
                                    int j = i + 2;
                                    errorNames = errorNames
                                            + bmdc_error_list.get(i);
                                    if (i != bmdc_error_list.size() - 1) {
                                        errorNames = errorNames + "\n\n" + j + ". ";
                                    }

                                }

//                                String bmdc_error = bmdc_error_list;
//                                String passwd_error = ;
                                String totalErrors = passwd_error_list + "\n" + passwd_error_list;

                                Log.d("tag11111", " totalErrors: " + totalErrors);


                                new MaterialDialog.Builder(LoginActivity.this)
                                        .title("Login Status")
                                        .content(totalErrors)
                                        .positiveText("")
                                        .negativeText("Ok")
                                        .show();


                            }

                        },
                        error -> {
                            Log.d("tag11111", " error: " + error.getMessage());
                        },
                        () -> {
                        }
                );

    }


}