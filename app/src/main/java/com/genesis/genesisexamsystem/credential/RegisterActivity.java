package com.genesis.genesisexamsystem.credential;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.genesis.genesisexamsystem.HomeActivity;
import com.genesis.genesisexamsystem.R;
import com.genesis.genesisexamsystem.data.shared_pref.SharedData;
import com.genesis.genesisexamsystem.model.ErrorResponseGeneric;
import com.genesis.genesisexamsystem.model.MedicalCollegeModel;
import com.genesis.genesisexamsystem.model.RegisterModel;
import com.genesis.genesisexamsystem.retrofit.RetrofitApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    ArrayAdapter<String> dataAdapter;
    Spinner spinner_medical_college;
    String sp2SelectedValue = "";
    String medical_college_id;
    List<String> categories2;
    HashMap<String, String> medicalCollegeIdMap;

    private ProgressDialog progressDialog;
    Button  btnRegister;
    EditText etFullName, etEmail, etPhoneNumber, etBmdcNumber, etPassword, etConfirmPassword;

    ImageView iv_user_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initview();
    }

    private void initview() {
        spinner_medical_college();

        progressDialog = new ProgressDialog(this);

        iv_user_image = findViewById(R.id.iv_user_image);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etBmdcNumber = findViewById(R.id.etBmdcNumber);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });

        iv_user_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProfilePicture();
            }
        });

    }


    private void spinner_medical_college() {

        spinner_medical_college = findViewById(R.id.spinner_medical_college);

        medicalCollegeApiCall();

        spinner_medical_college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View arg1, int position, long arg3) {
                String item = parent.getItemAtPosition(position).toString();
                sp2SelectedValue = item;
                if (!item.isEmpty() && item != null) {
                    medical_college_id = medicalCollegeIdMap.get(item);
                } else {
                }
                Log.d("tag4", "medical_college_id : " + medical_college_id);

                if (position == 0) {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorHint));
                } else {
                    ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.colorPrimary));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }


    @SuppressLint("CheckResult")
    public Void medicalCollegeApiCall() {

        categories2 = new ArrayList<String>();
        medicalCollegeIdMap = new HashMap<String, String>();

        String accept = "application/json";


        RetrofitApiClient.getApiInterface().medical_colleges(accept)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            if (response.code() == 200) {

                                String responseString = new String(response.message());
                                MedicalCollegeModel responseData = response.body();
                                List<MedicalCollegeModel.Data> medicalColleges = responseData.getData();

                                categories2.add("Select Medical College");

                                for (MedicalCollegeModel.Data medicalCollege : medicalColleges) {
                                    categories2.add(medicalCollege.getName());
                                    medicalCollegeIdMap.put(medicalCollege.getName(), medicalCollege.getId().toString());
                                    Log.d("tag4", "medicalCollege.getId(): " + medicalCollege.getId());
                                }

                                dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
                                dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                                spinner_medical_college.setAdapter(dataAdapter);
                            }
                        },
                        error -> {
                            Log.d("error_tag", "Error getting userdata :", error);
                        },
                        () -> {

                        }
                );
        return null;
    }


    private void registerAccount() {

        String fullName = etFullName.getText().toString();
        String email = etEmail.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String bmdcNumber = etBmdcNumber.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();


        if (TextUtils.isEmpty(medical_college_id) || medical_college_id == null || medical_college_id.equalsIgnoreCase("null")) {
            Toast.makeText(this, "Plz select medical college", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(fullName)) {
            Toast.makeText(this, "Plz fill up your full name field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Plz fill up your email field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "Plz fill up your phone number field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(bmdcNumber)) {
            Toast.makeText(this, "Plz fill up your bmdc number field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Plz fill up your password field", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Plz fill up your confirm password field", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setTitle("Register Account");
            progressDialog.setMessage("Plz wait, while we are checking the credentials...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            registrationApiCall(medical_college_id, fullName, email, phoneNumber, bmdcNumber, password, confirmPassword);
        }

    }

    @SuppressLint("CheckResult")
    private void registrationApiCall(String medical_college_id, String fullName, String email, String phoneNumber, String bmdcNumber, String password, String confirmPassword) {
        String accept = "application/json";

        RetrofitApiClient.getApiInterface().register(accept, fullName, phoneNumber, email, medical_college_id,  bmdcNumber, password, confirmPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            Log.d("tag11111", " response.code(): " + response.code());
                            Log.d("tag11111", " response: " + response);

                            progressDialog.dismiss();
                            if (response.code() == 403) {
                                Log.d("tag11111", " 403 is defined ");
                            }

                            RegisterModel registerModel = response.body();
                            Log.d("tag11111", " registerModel: " + registerModel);
                            String registerMessage = registerModel.getMessage();
                            Log.d("tag11111", " registerMessage: " + registerMessage);

                            if (response.isSuccessful()) {

//                                RegisterModel registerModel = response.body();
//                                Log.d("tag11111", " registerModel: " + registerModel);
//                                String registerMessage = registerModel.getMessage();
//                                Log.d("tag11111", " registerMessage: " + registerMessage);

                                response.body();
                                Log.d("tag11111", " response.body(): " + response.body());

                                RegisterModel.Data registerData = registerModel.getData();
                                String registerToken = registerModel.getToken();
                                String registerMessage2 = registerModel.getMessage();
                                String user_name = registerData.getName();
                                Log.d("tag11111", " registerToken: " + registerToken);
                                Log.d("tag11111", " registerMessage2: " + registerMessage2);

                                SharedData.saveTOKEN(this, registerToken);
                                SharedData.saveUSER_NAME(this, user_name);
                                SharedData.saveBMDC_NO(this, bmdcNumber);
                                SharedData.savePASSWORD(this, password);

                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
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

                                String errorNames = "";
                                for (int i = 0; i < bmdc_error_list.size(); i++) {
                                    int j = i + 2;
                                    errorNames = errorNames
                                            + bmdc_error_list.get(i);
                                    if (i != bmdc_error_list.size() - 1) {
                                        errorNames = errorNames + "\n\n" + j + ". ";
                                    }

                                }

                                String totalErrors = passwd_error_list + "\n" + passwd_error_list;
                                Log.d("tag11111", " totalErrors: " + totalErrors);

                                new MaterialDialog.Builder(RegisterActivity.this)
                                        .title("Register Status")
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


    ////image part starts
    private void chooseProfilePicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_profile_picture, null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageView imageViewADPPCamera = dialogView.findViewById(R.id.imageViewADPPCamera);
        ImageView imageViewADPPGallery = dialogView.findViewById(R.id.imageViewADPPGallery);

        final AlertDialog alertDialogProfilePicture = builder.create();
        alertDialogProfilePicture.show();

        imageViewADPPCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAndRequestPermissions()) {
                    takePictureFromCamera();
                    alertDialogProfilePicture.dismiss();
                }
            }
        });

        imageViewADPPGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureFromGallery();
                alertDialogProfilePicture.dismiss();
            }
        });
    }

    private void takePictureFromGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, 1);
    }

    private void takePictureFromCamera() {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImageUri = data.getData();
                    iv_user_image.setImageURI(selectedImageUri);
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    iv_user_image.setImageBitmap(bitmapImage);
                }
                break;
        }
    }

    private boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            int cameraPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (cameraPermission == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 20);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 20 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            takePictureFromCamera();
        } else
            Toast.makeText(this, "Permission not Granted", Toast.LENGTH_SHORT).show();
    }
    ////image part ends


}