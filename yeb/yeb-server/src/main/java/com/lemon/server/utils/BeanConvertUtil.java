package com.lemon.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * @des: 对象copy 工具类
 * @author: Lemon
 * @Date : 2021/5/27 17:40
 */
public class BeanConvertUtil {

    private static final Logger logger = LoggerFactory.getLogger(BeanConvertUtil.class);

    /**
     * 使用场景：Entity、Bo、Vo层数据的复制，因为BeanUtils.copyProperties只能给目标对象的属性赋值，却不能在List集合下循环赋值，因此添加该方法
     * 如：List<AdminEntity> 赋值到 List<AdminVo> ，List<AdminVo>中的 AdminVo 属性都会被赋予到值
     * S: 数据源类 ，T: 目标类::new(eg: AdminVo::new)
     *
     * @param sources
     * @param target
     * @param callBack
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target,
                                                    ListBeanUtilsCallBack<S, T> callBack) {

        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;

    }


    /**
     * 回调函数接口
     *
     * @param <S>
     * @param <T>
     */
    @FunctionalInterface
    public interface ListBeanUtilsCallBack<S, T> {
        void callBack(S t, T s);
    }

    /**
     * List对象拷贝
     *
     * @param sourceList
     * @param cla
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T, F> List<F> copyList(List<T> sourceList, Class<F> cla) {
        if (sourceList == null || sourceList.size() == 0) {
            return null;
        }
        List<F> returnList = new ArrayList<F>();
        for (T t : sourceList) {
            try {
                F f = cla.newInstance();
                copyProperties(t, f);
                returnList.add(f);
            } catch (Exception e) {
                logger.error("对象拷贝失败！", e);
            }
        }
        return returnList;
    }

    /**
     * 单个对象拷贝
     *
     * @param sourceBean
     * @param cla
     * @param <T>
     * @param <F>
     * @return
     */
    public static <T, F> F copyBean(T sourceBean, Class<F> cla) {
        if (sourceBean == null) {
            return null;
        }

        F f = null;
        try {

            f = cla.newInstance();
        } catch (Exception e) {
            logger.error("对象拷贝失败！", e);
        }
        return f;
    }

    /**
     * 对象 赋值
     *
     * @param sourceBean
     * @param targetBean
     * @param <T>
     * @param <F>
     */
    public static <T, F> void copyBeanValue(T sourceBean, F targetBean) {
        copyProperties(sourceBean, targetBean);
    }
}
