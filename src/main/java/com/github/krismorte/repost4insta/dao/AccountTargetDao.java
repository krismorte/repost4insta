/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.dao;

import com.github.krismorte.repost4insta.model.AccountTarget;
import java.util.List;

/**
 *
 * @author krisnamourtscf
 */
public class AccountTargetDao extends GenericDao<AccountTarget> {

    public AccountTargetDao() {
        super(AccountTarget.class);
    }

    public void doSave(AccountTarget target){
        beginTransaction();
        save(target);
        commitAndCloseTransaction();
    }
    
    public List<AccountTarget> list() {
        beginTransaction();
        List<AccountTarget> accounts = findAll();
        commitAndCloseTransaction();
        return accounts;
    }

    public List<AccountTarget> listByAccount(String accountName) {
        beginTransaction();
        List<AccountTarget> accounts = getEntityManager()
                .createQuery("select a from AccountTarget a where a.account = :account")
                .setParameter("account", accountName)
                .getResultList();
        commitAndCloseTransaction();
        return accounts;
    }

}
