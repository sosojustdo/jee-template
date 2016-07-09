package com.cloudyoung.web.view;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class JSONPView extends AbstractView {
    private String callBack;
    private Object json;
    private static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    }

    public JSONPView(String callBack, Object json) {
        this.callBack = callBack;
        this.json = json;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        StringBuilder stringBuilder = new StringBuilder(callBack).append("(").append(OBJECT_MAPPER.writeValueAsString(json)).append(")");
        PrintWriter out = response.getWriter();
        out.println(stringBuilder);
    }
}
