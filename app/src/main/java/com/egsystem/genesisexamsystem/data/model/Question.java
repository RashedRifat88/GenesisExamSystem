package com.egsystem.genesisexamsystem.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private int id;
    private String questionId;
    private String questionType;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;

    private String correct_ans_sba;
    private String correct_ans_a;
    private String correct_ans_b;
    private String correct_ans_c;
    private String correct_ans_d;
    private String correct_ans_e;

    public Question() {
    }


    public Question(String questionId, String questionType, String question, String option1, String option2, String option3, String option4,
                    String option5, String correct_ans_sba, String correct_ans_a, String correct_ans_b, String correct_ans_c, String correct_ans_d, String correct_ans_e) {
        this.questionId = questionId;
        this.questionType = questionType;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.correct_ans_sba = correct_ans_sba;
        this.correct_ans_a = correct_ans_a;
        this.correct_ans_b = correct_ans_b;
        this.correct_ans_c = correct_ans_c;
        this.correct_ans_d = correct_ans_d;
        this.correct_ans_e = correct_ans_e;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        questionId = in.readString();
        questionType = in.readString();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        option5 = in.readString();
        correct_ans_sba = in.readString();
        correct_ans_a = in.readString();
        correct_ans_b = in.readString();
        correct_ans_c = in.readString();
        correct_ans_d = in.readString();
        correct_ans_e = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(questionId);
        dest.writeString(questionType);
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeString(option5);
        dest.writeString(correct_ans_sba);
        dest.writeString(correct_ans_a);
        dest.writeString(correct_ans_b);
        dest.writeString(correct_ans_c);
        dest.writeString(correct_ans_d);
        dest.writeString(correct_ans_e);
    }
    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }
        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };


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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        return option3;
    }
    public void setOption3(String option3) {
        this.option3 = option3;
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
}