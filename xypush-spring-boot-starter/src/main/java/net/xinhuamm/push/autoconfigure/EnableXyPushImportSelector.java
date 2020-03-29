package net.xinhuamm.push.autoconfigure;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class EnableXyPushImportSelector implements ImportSelector {


    /**
     * support配置类
     */
    public static final String SUPPORT_DEFAULT_CONFIGURATION = "net.xinhuamm.push.autoconfigure.XyPushAutoConfiguration";

    /**
     * support启动配置
     */
    public static final String SUPPORT_ENABLE_ANNOTATION = "net.xinhuamm.push.autoconfigure.EnableXyPush";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        MultiValueMap<String, Object> valueMap = importingClassMetadata.getAllAnnotationAttributes(SUPPORT_ENABLE_ANNOTATION);
        List<Object> enableFalgList = valueMap.get("value");
        boolean enableFlag = (boolean) enableFalgList.get(0);
        if (!enableFlag) {
            return new String[]{};
        }
        Set<String> configuration = new HashSet<>();
        configuration.add(SUPPORT_DEFAULT_CONFIGURATION);
        String[] configComponent = new String[configuration.size()];
        configuration.toArray(configComponent);
        return enableFlag ? new String[]{SUPPORT_DEFAULT_CONFIGURATION} : new String[]{};
    }
}