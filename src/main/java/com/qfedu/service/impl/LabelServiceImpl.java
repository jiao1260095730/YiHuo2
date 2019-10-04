package com.qfedu.service.impl;

import com.qfedu.mapper.LabelMapper;
import com.qfedu.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 周立雄
 * @date 2019/10/4 11:55
 */
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelMapper labelMapper;

    public int getIdByName(String labelName) {
        int labelId = labelMapper.getIdByName(labelName);

        return labelId;
    }
}
