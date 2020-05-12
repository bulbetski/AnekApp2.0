package com.example.kursach;

public class Post {
    private int id;
    private int date;
    private int domain;
    private String text;
    private int likes;

    public Post() {
    }

    public Post(int id, int date, int domain, String text, int likes) {
        this.id = id;
        this.date = date;
        this.domain = domain;
        this.text = text;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDomain() {
        return domain;
    }

    public void setDomain(int domain) {
        this.domain = domain;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", date=" + date +
                ", domain=" + domain +
                ", text='" + text + '\'' +
                ", likes=" + likes +
                '}';
    }
}
