package com.alibaba.bean.msg.rsp;

/**
 * 音乐消息
 * @author KeChu
 *
 */
public class MusicMessage extends BaseMessage {  
    // 音乐   
    private Music Music;  
   
    public Music getMusic() {  
        return Music;  
    }  
   
    public void setMusic(Music music) {  
        Music = music;  
    }  
}