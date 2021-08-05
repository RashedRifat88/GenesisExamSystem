package com.genesis.genesisexamsystem.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

    private int id;
    private String questionId;
    private String exam_question_id;
    private String questionSl;
    private String questionType;
    private String correct_ans_sba;
    private String correct_ans_a;
    private String correct_ans_b;
    private String correct_ans_c;
    private String correct_ans_d;
    private String correct_ans_e;
    private String skipped;
    private String not_answered;

    public Answer() {
    }

    public Answer(String questionId, String exam_question_id, String questionSl, String questionType, String correct_ans_sba, String correct_ans_a, String correct_ans_b,
                  String correct_ans_c, String correct_ans_d, String correct_ans_e, String skipped, String not_answered) {
        this.id = id;
        this.questionId = questionId;
        this.exam_question_id = exam_question_id;
        this.questionSl = questionSl;
        this.questionType = questionType;
        this.correct_ans_sba = correct_ans_sba;
        this.correct_ans_a = correct_ans_a;
        this.correct_ans_b = correct_ans_b;
        this.correct_ans_c = correct_ans_c;
        this.correct_ans_d = correct_ans_d;
        this.correct_ans_e = correct_ans_e;
        this.skipped = skipped;
        this.not_answered = not_answered;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getExam_question_id() {
        return exam_question_id;
    }

    public void setExam_question_id(String exam_question_id) {
        this.exam_question_id = exam_question_id;
    }

    public String getQuestionSl() {
        return questionSl;
    }

    public void setQuestionSl(String questionSl) {
        this.questionSl = questionSl;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getCorrect_ans_sba() {
        return correct_ans_sba;
    }

    public void setCorrect_ans_sba(String correct_ans_sba) {
        this.correct_ans_sba = correct_ans_sba;
    }

    public String getCorrect_ans_a() {
        return correct_ans_a;
    }

    public void setCorrect_ans_a(String correct_ans_a) {
        this.correct_ans_a = correct_ans_a;
    }

    public String getCorrect_ans_b() {
        return correct_ans_b;
    }

    public void setCorrect_ans_b(String correct_ans_b) {
        this.correct_ans_b = correct_ans_b;
    }

    public String getCorrect_ans_c() {
        return correct_ans_c;
    }

    public void setCorrect_ans_c(String correct_ans_c) {
        this.correct_ans_c = correct_ans_c;
    }

    public String getCorrect_ans_d() {
        return correct_ans_d;
    }

    public void setCorrect_ans_d(String correct_ans_d) {
        this.correct_ans_d = correct_ans_d;
    }

    public String getCorrect_ans_e() {
        return correct_ans_e;
    }

    public void setCorrect_ans_e(String correct_ans_e) {
        this.correct_ans_e = correct_ans_e;
    }

    public String getSkipped() {
        return skipped;
    }

    public void setSkipped(String skipped) {
        this.skipped = skipped;
    }

    public String getNot_answered() {
        return not_answered;
    }

    public void setNot_answered(String not_answered) {
        this.not_answered = not_answered;
    }

    /////
    protected Answer(Parcel in) {
        id = in.readInt();
        questionId = in.readString();
        exam_question_id = in.readString();
        questionSl = in.readString();
        questionType = in.readString();
        correct_ans_sba = in.readString();
        correct_ans_a = in.readString();
        correct_ans_b = in.readString();
        correct_ans_c = in.readString();
        correct_ans_d = in.readString();
        correct_ans_e = in.readString();
        skipped = in.readString();
        not_answered = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(questionId);
        dest.writeString(exam_question_id);
        dest.writeString(questionSl);
        dest.writeString(questionType);
        dest.writeString(correct_ans_sba);
        dest.writeString(correct_ans_a);
        dest.writeString(correct_ans_b);
        dest.writeString(correct_ans_c);
        dest.writeString(correct_ans_d);
        dest.writeString(correct_ans_e);
        dest.writeString(skipped);
        dest.writeString(not_answered);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
