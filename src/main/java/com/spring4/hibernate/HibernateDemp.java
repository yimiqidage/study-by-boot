package com.spring4.hibernate;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 * Creator weishi8
 * Date&Time 2019-07-05 11:08
 * description
 */
public class HibernateDemp {

    /**
     * 目的：
     * 1.增加异常转换功能；
     * 2.捕获异常，并且以Spring非检查型异常的形式抛出；
     * 3.xml配置形式，参照'PETP_xml配置'
     * @return
     */
//    @Bean
//    public BeanPostProcessor persistenceTranslation(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

    /**
     * PETP_xml配置：
     * <bean class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor"/>
     *
     */

}
