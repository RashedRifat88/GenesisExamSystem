package com.genesis.genesisexamsystem.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuestionFileModel {

    @SerializedName("file_exists")
    @Expose
    private Boolean fileExists;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("url")
    @Expose
    private String url;

    public Boolean getFileExists() {
        return fileExists;
    }

    public void setFileExists(Boolean fileExists) {
        this.fileExists = fileExists;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public class Question {

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

    }

}