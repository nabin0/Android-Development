package com.nabin.blog;

import com.nabin.blog.BlogData.Blog;

import java.util.List;

public interface BlogArticlesCallback {
    void onSuccess(List<Blog> blogList);
    void onError();
}
