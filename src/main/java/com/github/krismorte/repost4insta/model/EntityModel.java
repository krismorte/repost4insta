/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.krismorte.repost4insta.model;

import com.towel.el.annotation.Resolvable;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 *
 * @author krisnamourtscf
 */
@MappedSuperclass
@Data
public class EntityModel  implements Serializable {
 
    @Resolvable(colName = "ID")
    @Id
    private final String id;
    
    
    public EntityModel() {
        this.id = UUID.randomUUID().toString();
    }    
    
}
