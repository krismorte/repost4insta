/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.util;

import java.io.File;

/**
 *
 * @author krisnamourtscf
 */
public class DirTree {

    public static final String ROOT_DIR_NAME = "DATA";
    public static final String ROOT_DIR_IMAGE = "IMAGE";

    public static void verify() {
        if (!new File(ROOT_DIR_NAME).exists()) {
            new File(ROOT_DIR_NAME).mkdir();
            new File(ROOT_DIR_NAME+"/"+ROOT_DIR_IMAGE).mkdir();
        }
    }

}
