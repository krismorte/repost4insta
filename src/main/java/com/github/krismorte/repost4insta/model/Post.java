/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.model;

import lombok.Data;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;

/**
 *
 * @author krismorte
 */
@Data
public class Post {
       
    private InstagramFeedItem feed;
    
    public static Post instaPost(InstagramFeedItem feed){
        Post post= new Post();
        post.setFeed(feed);
        return post;
    }
    
}
