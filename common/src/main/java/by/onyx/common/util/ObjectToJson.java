package by.onyx.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
public class ObjectToJson {

    public static void genObjectToJson(HttpServletResponse resp, Object result){
        ObjectMapper mapper = new ObjectMapper();
        try {
            PrintWriter out = resp.getWriter();
            String json = mapper.writeValueAsString(result);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        } catch (Exception e) {
            log.error("cant send json " + e);
        }
    }
}
