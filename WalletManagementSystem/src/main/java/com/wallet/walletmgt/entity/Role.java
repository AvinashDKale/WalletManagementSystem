package com.wallet.walletmgt.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Role() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Role(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    
}