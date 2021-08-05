package com.genesis.genesisexamsystem.retrofit;


import com.genesis.genesisexamsystem.model.DoctorCourseModel;
import com.genesis.genesisexamsystem.model.DoctorExamListModel;
import com.genesis.genesisexamsystem.model.LoginModel;
import com.genesis.genesisexamsystem.model.MedicalCollegeModel;
import com.genesis.genesisexamsystem.model.QuestionFileModel;
import com.genesis.genesisexamsystem.model.RegisterModel;
import com.genesis.genesisexamsystem.model.SubmitAnswerModel;
import com.google.gson.JsonObject;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {


    @FormUrlEncoded
    @POST(Api.user_login)
    Observable<Response<LoginModel>> login(@Header("Accept") String accept,
                                           @Field("bmdc_no") String device_id,
                                           @Field("password") String type
    );


    @FormUrlEncoded
    @POST(Api.user_registration)
    Observable<Response<RegisterModel>> register(@Header("Accept") String accept,
                                                 @Field("name") String name,
                                                 @Field("mobile_number") String mobile_number,
                                                 @Field("email") String email,
                                                 @Field("medical_college_id") String medical_college_id,
                                                 @Field("bmdc_no") String bmdc_no,
                                                 @Field("password") String password,
                                                 @Field("password_confirmation") String password_confirmation
    );


    @GET(Api.medical_colleges)
    Observable<Response<MedicalCollegeModel>> medical_colleges(@Header("Accept") String accept);


    @GET(Api.doctor_exam_courses)
    Observable<Response<DoctorCourseModel>> getDoctorCourses(
            @Header("Authorization") String authorization,
            @Header("Accept") String accept
    );


    @GET()
    Observable<Response<DoctorExamListModel>> getExamList(@Url String urlString,
                                                          @Header("authorization") String authorization,
                                                          @Header("Accept") String accept);



    @GET()
    Observable<Response<QuestionFileModel>> getQuestionFileApiCall(@Url String urlString,
                                                                   @Header("authorization") String authorization,
                                                                   @Header("Accept") String accept);




    @Headers("Content-Type: application/json")
    @POST()
    Observable<Response<SubmitAnswerModel>> submitGivenAnswer(@Url String urlString,
                                                              @Header("authorization") String authorization,
                                                              @Header("Accept") String accept,
                                                              @Body JsonObject body);


//    @Headers("Content-Type: multipart/form-data")
////    @FormUrlEncoded
//    @Multipart
//    @POST(Api.upload_records_of_report)
//    Observable<Response<RecordUpload>> upload_records_of_report(@Header("Authorization") String authorization,
//                                                                @Part List<MultipartBody.Part> files,
//                                                                @Part List<MultipartBody.Part> files_category,
//                                                                @Part("name") RequestBody name,
//                                                                @Query("title") String title,
//                                                                @Query("recordType") String recordType,
//                                                                @Query("recordDate") String recordDate
//
//    );


//    @Multipart
//    @POST(Api.upload_records_of_report)
//    Observable<Response<RecordUpload>> upload_records_of_report(@Header("Authorization") String authorization,
//                                                                @Part MultipartBody.Part file,
//                                                                @Part("name") RequestBody name
//    );


//    @GET(Api.all_article)
//    Observable<Response<ArticleModel>> getAllArticles();


//    @Headers("Content-Type: application/json")
//    @POST(Api.report_issue_msg)
//    Observable<Response<HelpCenter>> send_report_issue_msg(@Body JsonObject body,
//                                                           @Header("authorization") String authorization);
//

//    @GET(Api.invitation)
//    Observable<Response<Invitation>> get_invitation(@Header("Authorization") String authorization,
//                                                    @Query("pageNumber") String pageNumber
//    );


//    @GET()
//    Observable<Response<DoctorDetailsById>> getDoctorDetailsById(@Url String urlString2,
//                                                                 @Header("authorization") String authorization);


}

