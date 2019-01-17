package com.mmall.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName : ApplicationContextHelper
 * Created with IDEA
 * @author:CarlLing
 * @CreateDate : 2019-01-12 22:19
 * @Description : 获取 Spring 上下文工具
 *
 *  Spring中提供一些Aware结尾相关接口，像是BeanFactoryAware、 BeanNameAware、ApplicationContextAware、ResourceLoaderAware、
 *  ServletContextAware等等。实现这些 Aware接口的Bean在被实例化之后，可以取得一些相对应的资源
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware{

    private  static ApplicationContext applicationContext;

    /**
     * 系统启动时，会注入全局的ApplicationContext，上下文信息就获取到了
     * @param context
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 获取bean
     * @param clazzz
     * @param <T>
     * @return
     */
    public static <T> T popBean(Class<T> clazzz){
        if (applicationContext == null){
            return null;
        }
        return applicationContext.getBean(clazzz);
    }

    /**
     *  根据名称，获取bean
     * @param name 名称
     * @param clazz bean的类型
     * @param <T> 返回一个bean
     * @return
     */
    public static <T> T popBean(String name,Class<T> clazz){
        if (applicationContext == null){
            return  null;
        }
        return  applicationContext.getBean(name,clazz);
    }
}
