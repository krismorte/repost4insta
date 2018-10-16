/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.view.tables;

import com.github.krismorte.repost4insta.model.ScreenAccount;

/**
 *
 * @author krisnamourtscf
 */
public class ScreenAccountTables extends TablesHelper<ScreenAccount> {

    public ScreenAccountTables() {
        super(ScreenAccount.class, "selected,type,username");
    }
}
