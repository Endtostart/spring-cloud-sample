package cloud.euraka.customer.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanContextUtils.applicationContext = applicationContext;
    }

    /**
     * 根据bean name获取bean
     * @param name
     * @return
     */
    public static Object getBeanByName(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 根据bean name 和 类型获取bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBeanByNameWithType(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

}
