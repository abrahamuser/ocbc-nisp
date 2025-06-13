package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class QuestionBean implements Serializable {
    private String code;
    private Integer page;
    private String description;
    private String isMultipleAnswer;
    private List<AnswerBean> listAnswer;
    private List<AnswerBean> answerByUser;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public String getIsMultipleAnswer() {
        return isMultipleAnswer;
    }

    public void setIsMultipleAnswer(String isMultipleAnswer) {
        this.isMultipleAnswer = isMultipleAnswer;
    }

    public List<AnswerBean> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<AnswerBean> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public List<AnswerBean> getAnswerByUser() {
        return answerByUser;
    }

    public void setAnswerByUser(List<AnswerBean> answerByUser) {
        this.answerByUser = answerByUser;
    }
}
