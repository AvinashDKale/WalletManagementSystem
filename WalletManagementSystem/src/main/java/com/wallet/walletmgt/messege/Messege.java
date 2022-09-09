package com.wallet.walletmgt.messege;

public class Messege {
    private String content;
    private String type;
    
    public Messege() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public Messege(String content, String type) {
        super();
        this.content = content;
        this.type = type;
    }
    
    public String getContent() {
        return content;
    }
    
    public String getType() {
        return type;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
}
