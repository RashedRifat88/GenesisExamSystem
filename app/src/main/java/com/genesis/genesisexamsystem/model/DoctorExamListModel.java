package com.genesis.genesisexamsystem.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorExamListModel {

    @SerializedName("batch_type")
    @Expose
    private String batchType;
    @SerializedName("exams")
    @Expose
    private List<Exam> exams = null;

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }


    public class Exam {

        @SerializedName("year")
        @Expose
        private Integer year;
        @SerializedName("session_id")
        @Expose
        private Integer sessionId;
        @SerializedName("institute_id")
        @Expose
        private Integer instituteId;
        @SerializedName("course_id")
        @Expose
        private Integer courseId;
        @SerializedName("faculty_id")
        @Expose
        private Integer facultyId;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("batch_id")
        @Expose
        private Integer batchId;
        @SerializedName("branch_id")
        @Expose
        private Integer branchId;
        @SerializedName("created_at")
        @Expose
        private Integer createdAt;
        @SerializedName("updated_at")
        @Expose
        private Integer updatedAt;
        @SerializedName("exam_batch_id")
        @Expose
        private Integer examBatchId;
        @SerializedName("exam_id")
        @Expose
        private Integer examId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("exam_date")
        @Expose
        private String examDate;
        @SerializedName("exam_file_link")
        @Expose
        private String examFileLink;
        @SerializedName("doctorExamStatus")
        @Expose
        private String doctorExamStatus;
        @SerializedName("mcq_number")
        @Expose
        private Integer mcqNumber;
        @SerializedName("mcq_mark")
        @Expose
        private Double mcqMark;
        @SerializedName("mcq_negative_mark")
        @Expose
        private Double mcqNegativeMark;
        @SerializedName("sba_number")
        @Expose
        private Integer sbaNumber;
        @SerializedName("sba_mark")
        @Expose
        private Double sbaMark;
        @SerializedName("sba_negative_mark")
        @Expose
        private Double sbaNegativeMark;
        @SerializedName("pass_mark")
        @Expose
        private Integer passMark;
        @SerializedName("full_mark")
        @Expose
        private Integer fullMark;
        @SerializedName("duration")
        @Expose
        private Integer duration;

        @SerializedName("result ")
        @Expose
        private Result result;

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
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

        public Integer getBatchId() {
            return batchId;
        }

        public void setBatchId(Integer batchId) {
            this.batchId = batchId;
        }

        public Integer getBranchId() {
            return branchId;
        }

        public void setBranchId(Integer branchId) {
            this.branchId = branchId;
        }

        public Integer getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Integer createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Integer updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Integer getExamBatchId() {
            return examBatchId;
        }

        public void setExamBatchId(Integer examBatchId) {
            this.examBatchId = examBatchId;
        }

        public Integer getExamId() {
            return examId;
        }

        public void setExamId(Integer examId) {
            this.examId = examId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExamDate() {
            return examDate;
        }

        public void setExamDate(String examDate) {
            this.examDate = examDate;
        }

        public String getExamFileLink() {
            return examFileLink;
        }

        public void setExamFileLink(String examFileLink) {
            this.examFileLink = examFileLink;
        }

        public String getDoctorExamStatus() {
            return doctorExamStatus;
        }

        public void setDoctorExamStatus(String doctorExamStatus) {
            this.doctorExamStatus = doctorExamStatus;
        }

        public Integer getMcqNumber() {
            return mcqNumber;
        }

        public void setMcqNumber(Integer mcqNumber) {
            this.mcqNumber = mcqNumber;
        }

        public Double getMcqMark() {
            return mcqMark;
        }

        public void setMcqMark(Double mcqMark) {
            this.mcqMark = mcqMark;
        }

        public Double getMcqNegativeMark() {
            return mcqNegativeMark;
        }

        public void setMcqNegativeMark(Double mcqNegativeMark) {
            this.mcqNegativeMark = mcqNegativeMark;
        }

        public Integer getSbaNumber() {
            return sbaNumber;
        }

        public void setSbaNumber(Integer sbaNumber) {
            this.sbaNumber = sbaNumber;
        }

        public Double getSbaMark() {
            return sbaMark;
        }

        public void setSbaMark(Double sbaMark) {
            this.sbaMark = sbaMark;
        }

        public Double getSbaNegativeMark() {
            return sbaNegativeMark;
        }

        public void setSbaNegativeMark(Double sbaNegativeMark) {
            this.sbaNegativeMark = sbaNegativeMark;
        }

        public Integer getPassMark() {
            return passMark;
        }

        public void setPassMark(Integer passMark) {
            this.passMark = passMark;
        }

        public Integer getFullMark() {
            return fullMark;
        }

        public void setFullMark(Integer fullMark) {
            this.fullMark = fullMark;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

    }


    public class Result {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("doctor_course_id")
        @Expose
        private Integer doctorCourseId;
        @SerializedName("doctor_package_id")
        @Expose
        private Integer doctorPackageId;
        @SerializedName("exam_id")
        @Expose
        private Integer examId;
        @SerializedName("batch_id")
        @Expose
        private Integer batchId;
        @SerializedName("subject_id")
        @Expose
        private Integer subjectId;
        @SerializedName("faculty_id")
        @Expose
        private Integer facultyId;
        @SerializedName("year")
        @Expose
        private Integer year;
        @SerializedName("correct_mark")
        @Expose
        private Double correctMark;
        @SerializedName("negative_mark")
        @Expose
        private Double negativeMark;
        @SerializedName("obtained_mark")
        @Expose
        private Double obtainedMark;
        @SerializedName("obtained_mark_decimal")
        @Expose
        private Integer obtainedMarkDecimal;
        @SerializedName("wrong_answers")
        @Expose
        private Integer wrongAnswers;
        @SerializedName("highest_mark")
        @Expose
        private Double highestMark;
        @SerializedName("overall_position")
        @Expose
        private String overallPosition;
        @SerializedName("subject_position")
        @Expose
        private String subjectPosition;
        @SerializedName("batch_position")
        @Expose
        private String batchPosition;
        @SerializedName("candidate_position")
        @Expose
        private String candidatePosition;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDoctorCourseId() {
            return doctorCourseId;
        }

        public void setDoctorCourseId(Integer doctorCourseId) {
            this.doctorCourseId = doctorCourseId;
        }

        public Integer getDoctorPackageId() {
            return doctorPackageId;
        }

        public void setDoctorPackageId(Integer doctorPackageId) {
            this.doctorPackageId = doctorPackageId;
        }

        public Integer getExamId() {
            return examId;
        }

        public void setExamId(Integer examId) {
            this.examId = examId;
        }

        public Integer getBatchId() {
            return batchId;
        }

        public void setBatchId(Integer batchId) {
            this.batchId = batchId;
        }

        public Integer getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(Integer subjectId) {
            this.subjectId = subjectId;
        }

        public Integer getFacultyId() {
            return facultyId;
        }

        public void setFacultyId(Integer facultyId) {
            this.facultyId = facultyId;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Double getCorrectMark() {
            return correctMark;
        }

        public void setCorrectMark(Double correctMark) {
            this.correctMark = correctMark;
        }

        public Double getNegativeMark() {
            return negativeMark;
        }

        public void setNegativeMark(Double negativeMark) {
            this.negativeMark = negativeMark;
        }

        public Double getObtainedMark() {
            return obtainedMark;
        }

        public void setObtainedMark(Double obtainedMark) {
            this.obtainedMark = obtainedMark;
        }

        public Integer getObtainedMarkDecimal() {
            return obtainedMarkDecimal;
        }

        public void setObtainedMarkDecimal(Integer obtainedMarkDecimal) {
            this.obtainedMarkDecimal = obtainedMarkDecimal;
        }

        public Integer getWrongAnswers() {
            return wrongAnswers;
        }

        public void setWrongAnswers(Integer wrongAnswers) {
            this.wrongAnswers = wrongAnswers;
        }

        public Double getHighestMark() {
            return highestMark;
        }

        public void setHighestMark(Double highestMark) {
            this.highestMark = highestMark;
        }

        public String getOverallPosition() {
            return overallPosition;
        }

        public void setOverallPosition(String overallPosition) {
            this.overallPosition = overallPosition;
        }

        public String getSubjectPosition() {
            return subjectPosition;
        }

        public void setSubjectPosition(String subjectPosition) {
            this.subjectPosition = subjectPosition;
        }

        public String getBatchPosition() {
            return batchPosition;
        }

        public void setBatchPosition(String batchPosition) {
            this.batchPosition = batchPosition;
        }

        public String getCandidatePosition() {
            return candidatePosition;
        }

        public void setCandidatePosition(String candidatePosition) {
            this.candidatePosition = candidatePosition;
        }

    }




}