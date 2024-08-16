package com.flandre923.mods923.mathQuestion;

public class Answer {
    public int id;
    public String content;

    public Answer(int id,String content) {
        this.id = id;
        this.content = content;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public int getId(){
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Answer answer){
            return answer.getId() == this.id;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return this.id + " " + this.content;
    }
}
