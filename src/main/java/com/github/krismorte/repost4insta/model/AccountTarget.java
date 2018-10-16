/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.model;

import com.towel.el.annotation.Resolvable;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Data;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

/**
 *
 * @author krismorte
 */
@Entity
@Data
public class AccountTarget extends EntityModel{

    @Transient
    private InstagramUser instaUser;
    private String account;
    @Resolvable(colName = "Id")
    private Long targetId;
    @Resolvable(colName = "Username")
    private String userName;

    public static AccountTarget getInstaTarget(InstagramAccount account, InstagramUser instaUser) {
        AccountTarget accountTarget = new AccountTarget();
        accountTarget.setUserName(instaUser.getUsername());
        accountTarget.setAccount(account.getUsername());
        accountTarget.setTargetId(instaUser.getPk());
        accountTarget.setInstaUser(instaUser);
        return accountTarget;
    }


}
