package com.qfedu.entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class Community {
    private int id;
    private String communityName;
    private Date createTime;
    private String communityDesc;
    private String videoImgUrl;
    private String playNum;
    private String userId;

}
