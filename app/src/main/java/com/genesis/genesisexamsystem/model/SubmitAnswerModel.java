package com.genesis.genesisexamsystem.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubmitAnswerModel {

    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("answers")
    @Expose
    private List<Answer> answers = null;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public class Answer {

        @SerializedName("question_id")
        @Expose
        private Integer questionId;
        @SerializedName("exam_question_id")
        @Expose
        private Integer examQuestionId;
        @SerializedName("question_type")
        @Expose
        private String questionType;
        @SerializedName("correct_ans_sba")
        @Expose
        private String correctAnsSba;
        @SerializedName("question_title")
        @Expose
        private String questionTitle;
        @SerializedName("question_option")
        @Expose
        private List<QuestionOption> questionOption = null;
        @SerializedName("doctor_answer_sba")
        @Expose
        private String doctorAnswerSba;

        public Integer getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Integer questionId) {
            this.questionId = questionId;
        }

        public Integer getExamQuestionId() {
            return examQuestionId;
        }

        public void setExamQuestionId(Integer examQuestionId) {
            this.examQuestionId = examQuestionId;
        }

        public String getQuestionType() {
            return questionType;
        }

        public void setQuestionType(String questionType) {
            this.questionType = questionType;
        }

        public String getCorrectAnsSba() {
            return correctAnsSba;
        }

        public void setCorrectAnsSba(String correctAnsSba) {
            this.correctAnsSba = correctAnsSba;
        }

        public String getQuestionTitle() {
            return questionTitle;
        }

        public void setQuestionTitle(String questionTitle) {
            this.questionTitle = questionTitle;
        }

        public List<QuestionOption> getQuestionOption() {
            return questionOption;
        }

        public void setQuestionOption(List<QuestionOption> questionOption) {
            this.questionOption = questionOption;
        }

        public String getDoctorAnswerSba() {
            return doctorAnswerSba;
        }

        public void setDoctorAnswerSba(String doctorAnswerSba) {
            this.doctorAnswerSba = doctorAnswerSba;
        }

    }


    public class QuestionOption {

        @SerializedName("option_serial")
        @Expose
        private String optionSerial;
        @SerializedName("option_title")
        @Expose
        private String optionTitle;
        @SerializedName("correct_ans")
        @Expose
        private String correctAns;
        @SerializedName("doctor_ans")
        @Expose
        private String doctorAns;

        public String getOptionSerial() {
            return optionSerial;
        }

        public void setOptionSerial(String optionSerial) {
            this.optionSerial = optionSerial;
        }

        public String getOptionTitle() {
            return optionTitle;
        }

        public void setOptionTitle(String optionTitle) {
            this.optionTitle = optionTitle;
        }

        public String getCorrectAns() {
            return correctAns;
        }

        public void setCorrectAns(String correctAns) {
            this.correctAns = correctAns;
        }

        public String getDoctorAns() {
            return doctorAns;
        }

        public void setDoctorAns(String doctorAns) {
            this.doctorAns = doctorAns;
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

    }


}
