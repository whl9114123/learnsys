package com.whl.common.param;

import lombok.Setter;

@Setter
public class PageParam {
    private String page;
    private String limit;

    public Long getPage() {
        return Long.parseLong(page);
    }

    public Long getLimit() {
        return Long.parseLong(limit);
    }
}
