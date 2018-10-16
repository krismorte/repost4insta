/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author krismorte
 */
@Entity
@Data
@NoArgsConstructor
public class StatusReposted extends EntityModel {
    
    private LocalDate date;
    private String account;
    private long postId;

    public StatusReposted(String account, long postId) {
        super();
        this.date = LocalDate.now();
        this.account = account;
        this.postId = postId;
    }
    
    
    
}
