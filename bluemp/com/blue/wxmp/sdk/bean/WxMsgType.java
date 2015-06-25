package com.blue.wxmp.sdk.bean;

public enum WxMsgType {
	text("text",1),image("image",2),voice("voice",3),video("video",4),location("location",5),link("link",7),event("event",8),
	// for out
	music("music",9),news("news",10);
	
	private String name;  
    private int index;  
    
    private WxMsgType(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.name;  
    }  
}
