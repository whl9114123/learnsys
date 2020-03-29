package net.xinhuamm.push.autoconfigure.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("xhmm.push")
@Data
public class XyPushProperties {
    private  String name;

}
