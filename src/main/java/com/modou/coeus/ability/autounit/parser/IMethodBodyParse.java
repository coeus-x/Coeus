package com.modou.coeus.ability.autounit.parser;


import com.modou.coeus.ability.autounit.UnitMethodPair;
import com.modou.coeus.model.autounit.context.UnitMockContext;

public interface IMethodBodyParse {

    String parse(UnitMethodPair methodPair, UnitMockContext unitMockContext);

}
