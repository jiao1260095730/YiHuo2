package com.qfedu.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultInfo {
    private int code;
    private String msg;
    private String Info;
    private String url;

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", Info='" + Info + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
