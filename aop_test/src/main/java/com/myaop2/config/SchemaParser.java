package com.myaop2.config;

import java.util.ArrayList;
import java.util.List;

import com.myaop2.bean.Advisor;

public class SchemaParser implements ConfigParser {

    @Override
    public List<Advisor> parse() {
        return new ArrayList<Advisor>();
    }
    
}