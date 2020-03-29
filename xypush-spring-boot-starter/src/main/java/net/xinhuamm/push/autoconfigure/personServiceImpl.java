package net.xinhuamm.push.autoconfigure;

import net.xinhuamm.push.autoconfigure.properties.XyPushProperties;

public class personServiceImpl  implements PersonService{
    private String name;

    public personServiceImpl(XyPushProperties properties) {
        name = properties.getName();

    }
@Override
    public String sayHello() {
        return this.name;
    }
}
