/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.model;

/**
 *
 * @author krisnamourtscf
 */
public interface Account {
    
    public String getUsername();
    public String getUrlProfilePic();
    public void connect()throws Exception ;    
    public boolean isConnect();
    public AccountTarget getUser(String userName)throws Exception;
    public TimeLine getUserTimeline(Long id)throws Exception;
    
    public void repost(Post post)throws Exception;
    
}
