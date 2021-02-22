package com.egsystem.genesisexamsystem.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("token")
    @Expose
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public class Data {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("date_of_birth")
        @Expose
        private Object dateOfBirth;
        @SerializedName("gender")
        @Expose
        private Object gender;
        @SerializedName("rcp_reg_no")
        @Expose
        private Object rcpRegNo;
        @SerializedName("bmdc_no")
        @Expose
        private String bmdcNo;
        @SerializedName("mobile_number")
        @Expose
        private String mobileNumber;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("medical_college_id")
        @Expose
        private Integer medicalCollegeId;
        @SerializedName("blood_group")
        @Expose
        private Object bloodGroup;
        @SerializedName("facebook_id")
        @Expose
        private Object facebookId;
        @SerializedName("father_name")
        @Expose
        private Object fatherName;
        @SerializedName("mother_name")
        @Expose
        private Object motherName;
        @SerializedName("spouse_name")
        @Expose
        private Object spouseName;
        @SerializedName("job_description")
        @Expose
        private Object jobDescription;
        @SerializedName("nid")
        @Expose
        private Object nid;
        @SerializedName("passport")
        @Expose
        private Object passport;
        @SerializedName("chamber_address")
        @Expose
        private Object chamberAddress;
        @SerializedName("permanent_division_id")
        @Expose
        private Object permanentDivisionId;
        @SerializedName("permanent_district_id")
        @Expose
        private Object permanentDistrictId;
        @SerializedName("permanent_upazila_id")
        @Expose
        private Object permanentUpazilaId;
        @SerializedName("permanent_address")
        @Expose
        private Object permanentAddress;
        @SerializedName("present_division_id")
        @Expose
        private Object presentDivisionId;
        @SerializedName("present_district_id")
        @Expose
        private Object presentDistrictId;
        @SerializedName("present_upazila_id")
        @Expose
        private Object presentUpazilaId;
        @SerializedName("present_address")
        @Expose
        private Object presentAddress;
        @SerializedName("photo")
        @Expose
        private Object photo;
        @SerializedName("sign")
        @Expose
        private Object sign;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(Object dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getRcpRegNo() {
            return rcpRegNo;
        }

        public void setRcpRegNo(Object rcpRegNo) {
            this.rcpRegNo = rcpRegNo;
        }

        public String getBmdcNo() {
            return bmdcNo;
        }

        public void setBmdcNo(String bmdcNo) {
            this.bmdcNo = bmdcNo;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getMedicalCollegeId() {
            return medicalCollegeId;
        }

        public void setMedicalCollegeId(Integer medicalCollegeId) {
            this.medicalCollegeId = medicalCollegeId;
        }

        public Object getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(Object bloodGroup) {
            this.bloodGroup = bloodGroup;
        }

        public Object getFacebookId() {
            return facebookId;
        }

        public void setFacebookId(Object facebookId) {
            this.facebookId = facebookId;
        }

        public Object getFatherName() {
            return fatherName;
        }

        public void setFatherName(Object fatherName) {
            this.fatherName = fatherName;
        }

        public Object getMotherName() {
            return motherName;
        }

        public void setMotherName(Object motherName) {
            this.motherName = motherName;
        }

        public Object getSpouseName() {
            return spouseName;
        }

        public void setSpouseName(Object spouseName) {
            this.spouseName = spouseName;
        }

        public Object getJobDescription() {
            return jobDescription;
        }

        public void setJobDescription(Object jobDescription) {
            this.jobDescription = jobDescription;
        }

        public Object getNid() {
            return nid;
        }

        public void setNid(Object nid) {
            this.nid = nid;
        }

        public Object getPassport() {
            return passport;
        }

        public void setPassport(Object passport) {
            this.passport = passport;
        }

        public Object getChamberAddress() {
            return chamberAddress;
        }

        public void setChamberAddress(Object chamberAddress) {
            this.chamberAddress = chamberAddress;
        }

        public Object getPermanentDivisionId() {
            return permanentDivisionId;
        }

        public void setPermanentDivisionId(Object permanentDivisionId) {
            this.permanentDivisionId = permanentDivisionId;
        }

        public Object getPermanentDistrictId() {
            return permanentDistrictId;
        }

        public void setPermanentDistrictId(Object permanentDistrictId) {
            this.permanentDistrictId = permanentDistrictId;
        }

        public Object getPermanentUpazilaId() {
            return permanentUpazilaId;
        }

        public void setPermanentUpazilaId(Object permanentUpazilaId) {
            this.permanentUpazilaId = permanentUpazilaId;
        }

        public Object getPermanentAddress() {
            return permanentAddress;
        }

        public void setPermanentAddress(Object permanentAddress) {
            this.permanentAddress = permanentAddress;
        }

        public Object getPresentDivisionId() {
            return presentDivisionId;
        }

        public void setPresentDivisionId(Object presentDivisionId) {
            this.presentDivisionId = presentDivisionId;
        }

        public Object getPresentDistrictId() {
            return presentDistrictId;
        }

        public void setPresentDistrictId(Object presentDistrictId) {
            this.presentDistrictId = presentDistrictId;
        }

        public Object getPresentUpazilaId() {
            return presentUpazilaId;
        }

        public void setPresentUpazilaId(Object presentUpazilaId) {
            this.presentUpazilaId = presentUpazilaId;
        }

        public Object getPresentAddress() {
            return presentAddress;
        }

        public void setPresentAddress(Object presentAddress) {
            this.presentAddress = presentAddress;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
            this.sign = sign;
        }

    }


}