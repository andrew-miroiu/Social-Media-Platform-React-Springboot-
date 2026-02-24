package com.andrei.springboot.dto;

import java.util.*;

public class CreateCommentRequestDTO {

    private UUID postId;
    private String text;

     public UUID getPostId(){
        return postId;
    }

    public void setPostId(UUID postId){
        this.postId = postId;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
}