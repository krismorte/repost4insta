/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.dao;

import com.github.krismorte.repost4insta.model.InstagramAccount;
import java.util.List;

/**
 *
 * @author krisnamourtscf
 */
public class InstagramAccountDao extends GenericDao<InstagramAccount> {

    public InstagramAccountDao() {
        super(InstagramAccount.class);
    }

    public void doSave(InstagramAccount account) {
        beginTransaction();
        save(account);
        commitAndCloseTransaction();
    }

    public List<InstagramAccount> list() {
        beginTransaction();
        List<InstagramAccount> accounts = findAll();
        commitAndCloseTransaction();
        return accounts;
    }

     public InstagramAccount findByUserName(String userName) {
        beginTransaction();
        InstagramAccount accounts = getEntityManager()
                .createQuery("select a from InstagramAccount a where a.userName = :userName",InstagramAccount.class)
                .setParameter("userName", userName)
                .getSingleResult();
        commitAndCloseTransaction();
        return accounts;
    }
    
}
