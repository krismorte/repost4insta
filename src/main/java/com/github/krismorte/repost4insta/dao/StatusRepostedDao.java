/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.dao;

import com.github.krismorte.repost4insta.model.StatusReposted;
import java.util.List;

/**
 *
 * @author krisnamourtscf
 */
public class StatusRepostedDao extends GenericDao<StatusReposted> {

    public StatusRepostedDao() {
        super(StatusReposted.class);
    }

    public void doSave(StatusReposted account) {
        beginTransaction();
        save(account);
        commitAndCloseTransaction();
    }

    public List<StatusReposted> listByAccountAndId(String account, long id) {
        beginTransaction();
        List<StatusReposted> accounts = getEntityManager()
                .createQuery("select a from StatusReposted a where a.account = :account and a.postId = :id")
                .setParameter("account", account)
                .setParameter("id", id)
                .getResultList();
        commitAndCloseTransaction();
        return accounts;
    }
    
     public List<StatusReposted> listByAccount(String account) {
        beginTransaction();
        List<StatusReposted> accounts = getEntityManager()
                .createQuery("select a from StatusReposted a where a.account = :account ")
                .setParameter("account", account)
                .getResultList();
        commitAndCloseTransaction();
        return accounts;
    }

    public List<StatusReposted> list() {
        beginTransaction();
        List<StatusReposted> accounts = findAll();
        commitAndCloseTransaction();
        return accounts;
    }

}
