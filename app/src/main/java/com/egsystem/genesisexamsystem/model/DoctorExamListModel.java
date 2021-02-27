package com.egsystem.genesisexamsystem.model;

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
        @SerializedName("answer_file_link")
        @Expose
        private String answerFileLink;
        @SerializedName("isDoctorParticipated")
        @Expose
        private Boolean isDoctorParticipated;
        @SerializedName("mcq_number")
        @Expose
        private Integer mcqNumber;
        @SerializedName("mcq_mark")
        @Expose
        private Integer mcqMark;
        @SerializedName("mcq_negative_mark")
        @Expose
        private Integer mcqNegativeMark;
        @SerializedName("sba_number")
        @Expose
        private Integer sbaNumber;
        @SerializedName("sba_mark")
        @Expose
        private Integer sbaMark;
        @SerializedName("sba_negative_mark")
        @Expose
        private Integer sbaNegativeMark;
        @SerializedName("pass_mark")
        @Expose
        private Integer passMark;
        @SerializedName("full_mark")
        @Expose
        private Integer fullMark;
        @SerializedName("duration")
        @Expose
        private Integer duration;

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

        public String getAnswerFileLink() {
            return answerFileLink;
        }

        public void setAnswerFileLink(String answerFileLink) {
            this.answerFileLink = answerFileLink;
        }

        public Boolean getIsDoctorParticipated() {
            return isDoctorParticipated;
        }

        public void setIsDoctorParticipated(Boolean isDoctorParticipated) {
            this.isDoctorParticipated = isDoctorParticipated;
        }

        public Integer getMcqNumber() {
            return mcqNumber;
        }

        public void setMcqNumber(Integer mcqNumber) {
            this.mcqNumber = mcqNumber;
        }

        public Integer getMcqMark() {
            return mcqMark;
        }

        public void setMcqMark(Integer mcqMark) {
            this.mcqMark = mcqMark;
        }

        public Integer getMcqNegativeMark() {
            return mcqNegativeMark;
        }

        public void setMcqNegativeMark(Integer mcqNegativeMark) {
            this.mcqNegativeMark = mcqNegativeMark;
        }

        public Integer getSbaNumber() {
            return sbaNumber;
        }

        public void setSbaNumber(Integer sbaNumber) {
            this.sbaNumber = sbaNumber;
        }

        public Integer getSbaMark() {
            return sbaMark;
        }

        public void setSbaMark(Integer sbaMark) {
            this.sbaMark = sbaMark;
        }

        public Integer getSbaNegativeMark() {
            return sbaNegativeMark;
        }

        public void setSbaNegativeMark(Integer sbaNegativeMark) {
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

}