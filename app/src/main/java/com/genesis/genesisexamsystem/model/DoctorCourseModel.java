package com.genesis.genesisexamsystem.model;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DoctorCourseModel {

    @SerializedName("doctor_courses")
    @Expose
    private List<DoctorCourse> doctorCourses = null;

    public List<DoctorCourse> getDoctorCourses() {
        return doctorCourses;
    }

    public void setDoctorCourses(List<DoctorCourse> doctorCourses) {
        this.doctorCourses = doctorCourses;
    }


    public class DoctorCourse {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("doctor_id")
        @Expose
        private Integer doctorId;
        @SerializedName("reg_no")
        @Expose
        private String regNo;
        @SerializedName("reg_no_first_part")
        @Expose
        private String regNoFirstPart;
        @SerializedName("reg_no_last_part")
        @Expose
        private String regNoLastPart;
        @SerializedName("reg_no_last_part_int")
        @Expose
        private String regNoLastPartInt;
        @SerializedName("institute_id")
        @Expose
        private Integer instituteId;
        @SerializedName("course_id")
        @Expose
        private Integer courseId;
        @SerializedName("batch_id")
        @Expose
        private Integer batchId;
        @SerializedName("faculty_id")
        @Expose
        private Integer facultyId;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("branch_id")
        @Expose
        private Integer branchId;
        @SerializedName("candidate_type")
        @Expose
        private String candidateType;
        @SerializedName("actual_course_price")
        @Expose
        private Integer actualCoursePrice;
        @SerializedName("course_price")
        @Expose
        private Integer coursePrice;
        @SerializedName("include_lecture_sheet")
        @Expose
        private String includeLectureSheet;
        @SerializedName("payment_status")
        @Expose
        private String paymentStatus;
        @SerializedName("year")
        @Expose
        private Integer year;
        @SerializedName("session_id")
        @Expose
        private Integer sessionId;
        @SerializedName("session")
        @Expose
        private String session;
        @SerializedName("admission_type")
        @Expose
        private String admissionType;
        @SerializedName("service_package_id")
        @Expose
        private Integer servicePackageId;
        @SerializedName("is_discipline_changed")
        @Expose
        private String isDisciplineChanged;
        @SerializedName("refer_by")
        @Expose
        private String referBy;
        @SerializedName("course_name")
        @Expose
        private String courseName;
        @SerializedName("course_detail")
        @Expose
        private String courseDetail;
        @SerializedName("course_institute_id")
        @Expose
        private Integer courseInstituteId;
        @SerializedName("course_code")
        @Expose
        private String courseCode;
        @SerializedName("batch_name")
        @Expose
        private String batchName;
        @SerializedName("batch_details")
        @Expose
        private String batchDetails;
        @SerializedName("batch_type")
        @Expose
        private String batchType;
        @SerializedName("batch_code")
        @Expose
        private String batchCode;
        @SerializedName("batch_price")
        @Expose
        private Integer batchPrice;
        @SerializedName("batch_fee_type")
        @Expose
        private String batchFeeType;
        @SerializedName("batch_branch_id")
        @Expose
        private Integer batchBranchId;
        @SerializedName("batch_institute_id")
        @Expose
        private String batchInstituteId;
        @SerializedName("batch_subject_id")
        @Expose
        private String batchSubjectId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(Integer doctorId) {
            this.doctorId = doctorId;
        }

        public String getRegNo() {
            return regNo;
        }

        public void setRegNo(String regNo) {
            this.regNo = regNo;
        }

        public String getRegNoFirstPart() {
            return regNoFirstPart;
        }

        public void setRegNoFirstPart(String regNoFirstPart) {
            this.regNoFirstPart = regNoFirstPart;
        }

        public String getRegNoLastPart() {
            return regNoLastPart;
        }

        public void setRegNoLastPart(String regNoLastPart) {
            this.regNoLastPart = regNoLastPart;
        }

        public String getRegNoLastPartInt() {
            return regNoLastPartInt;
        }

        public void setRegNoLastPartInt(String regNoLastPartInt) {
            this.regNoLastPartInt = regNoLastPartInt;
        }

        public Integer getInstituteId() {
            return instituteId;
        }

        public void setInstituteId(Integer instituteId) {
            this.instituteId = instituteId;
        }

        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        public Integer getBatchId() {
            return batchId;
        }

        public void setBatchId(Integer batchId) {
            this.batchId = batchId;
        }

        public Integer getFacultyId() {
            return facultyId;
        }

        public void setFacultyId(Integer facultyId) {
            this.facultyId = facultyId;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public Integer getBranchId() {
            return branchId;
        }

        public void setBranchId(Integer branchId) {
            this.branchId = branchId;
        }

        public String getCandidateType() {
            return candidateType;
        }

        public void setCandidateType(String candidateType) {
            this.candidateType = candidateType;
        }

        public Integer getActualCoursePrice() {
            return actualCoursePrice;
        }

        public void setActualCoursePrice(Integer actualCoursePrice) {
            this.actualCoursePrice = actualCoursePrice;
        }

        public Integer getCoursePrice() {
            return coursePrice;
        }

        public void setCoursePrice(Integer coursePrice) {
            this.coursePrice = coursePrice;
        }

        public String getIncludeLectureSheet() {
            return includeLectureSheet;
        }

        public void setIncludeLectureSheet(String includeLectureSheet) {
            this.includeLectureSheet = includeLectureSheet;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public void setPaymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Integer getSessionId() {
            return sessionId;
        }

        public void setSessionId(Integer sessionId) {
            this.sessionId = sessionId;
        }

        public String getSession() {
            return session;
        }

        public void setSession(String session) {
            this.session = session;
        }

        public String getAdmissionType() {
            return admissionType;
        }

        public void setAdmissionType(String admissionType) {
            this.admissionType = admissionType;
        }

        public Integer getServicePackageId() {
            return servicePackageId;
        }

        public void setServicePackageId(Integer servicePackageId) {
            this.servicePackageId = servicePackageId;
        }

        public String getIsDisciplineChanged() {
            return isDisciplineChanged;
        }

        public void setIsDisciplineChanged(String isDisciplineChanged) {
            this.isDisciplineChanged = isDisciplineChanged;
        }

        public String getReferBy() {
            return referBy;
        }

        public void setReferBy(String referBy) {
            this.referBy = referBy;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseDetail() {
            return courseDetail;
        }

        public void setCourseDetail(String courseDetail) {
            this.courseDetail = courseDetail;
        }

        public Integer getCourseInstituteId() {
            return courseInstituteId;
        }

        public void setCourseInstituteId(Integer courseInstituteId) {
            this.courseInstituteId = courseInstituteId;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getBatchName() {
            return batchName;
        }

        public void setBatchName(String batchName) {
            this.batchName = batchName;
        }

        public String getBatchDetails() {
            return batchDetails;
        }

        public void setBatchDetails(String batchDetails) {
            this.batchDetails = batchDetails;
        }

        public String getBatchType() {
            return batchType;
        }

        public void setBatchType(String batchType) {
            this.batchType = batchType;
        }

        public String getBatchCode() {
            return batchCode;
        }

        public void setBatchCode(String batchCode) {
            this.batchCode = batchCode;
        }

        public Integer getBatchPrice() {
            return batchPrice;
        }

        public void setBatchPrice(Integer batchPrice) {
            this.batchPrice = batchPrice;
        }

        public String getBatchFeeType() {
            return batchFeeType;
        }

        public void setBatchFeeType(String batchFeeType) {
            this.batchFeeType = batchFeeType;
        }

        public Integer getBatchBranchId() {
            return batchBranchId;
        }

        public void setBatchBranchId(Integer batchBranchId) {
            this.batchBranchId = batchBranchId;
        }

        public String getBatchInstituteId() {
            return batchInstituteId;
        }

        public void setBatchInstituteId(String batchInstituteId) {
            this.batchInstituteId = batchInstituteId;
        }

        public String getBatchSubjectId() {
            return batchSubjectId;
        }

        public void setBatchSubjectId(String batchSubjectId) {
            this.batchSubjectId = batchSubjectId;
        }

    }


}

