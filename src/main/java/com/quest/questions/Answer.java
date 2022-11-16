package com.quest.questions;

import lombok.Getter;

@Getter
public class Answer {
    private String text;
    private boolean wrongAnswer;

    private String wrongAnswerEndText;

    public Answer(String text, String wrongAnswerEndText) {
        this.text = text;
        this.wrongAnswerEndText = wrongAnswerEndText;
        this.wrongAnswer = true;
    }

    public Answer(String text) {
        this.text = text;
        this.wrongAnswer = false;
    }
}
