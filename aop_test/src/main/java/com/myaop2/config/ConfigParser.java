package com.myaop2.config;

import java.util.List;

import com.myaop2.bean.Advisor;

public interface ConfigParser {
    
    public List<Advisor> parse();

}