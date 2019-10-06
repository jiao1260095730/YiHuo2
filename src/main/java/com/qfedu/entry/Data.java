package com.qfedu.entry;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {

    private int code;

    private String token;

    private String msg;

    private List<Object> data;
}
