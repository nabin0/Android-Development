package com.nabin.blog.BlogData;

import com.nabin.blog.BlogHttpClient;

import java.util.Objects;

public class Blog {
    private String id;
    private Author author;
    private String title;
    private String date;
    private String image;
    private  float rating;
    private String description;
    private int views;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Float.compare(blog.rating, rating) == 0 && views == blog.views && Objects.equals(id, blog.id) && Objects.equals(author, blog.author) && Objects.equals(title, blog.title) && Objects.equals(date, blog.date) && Objects.equals(image, blog.image) && Objects.equals(description, blog.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, date, image, rating, description, views);
    }

    public String getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getImageUrl(){
        return BlogHttpClient.BASE_URL + BlogHttpClient.PATH + getImage();
    }

    public float getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public int getViews() {
        return views;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
