package com.modou.coeus.model.autounit;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


public class UnitStrategyContent {

    /**
     * 类型对应的内容
     */
    private Map<UnitClassType, StringBuilder> content = new HashMap<>();

    public Map<UnitClassType, StringBuilder> getContent() {
        return content;
    }

    public void setContent(Map<UnitClassType, StringBuilder> content) {
        this.content = content;
    }
}
