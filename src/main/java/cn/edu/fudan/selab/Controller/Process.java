package cn.edu.fudan.selab.Controller;


import cn.edu.fudan.selab.Service.AbilityReasoning;
import cn.edu.fudan.selab.Service.ProcessDesign;
import cn.edu.fudan.selab.Service.ProcessExecute;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/process")
public class Process {

    public static final Logger logger = Logger.getLogger(Process.class);
    private static ExecutorService processExecute = Executors.newCachedThreadPool();

    @RequestMapping(value = "/execute", method = RequestMethod.GET)
    @ResponseBody
    public String execute(HttpServletRequest request) throws InterruptedException, UnsupportedEncodingException {
        String location = request.getParameter("location");
        AbilityReasoning abilityReasoning = new AbilityReasoning();
        String result = abilityReasoning.abilityMatch(abilityReasoning.abilitySearch(location));
        JSONObject resultJson = JSON.parseObject(result);
        logger.info(String.format("The result of call is [ %s ]", result));
        if (resultJson.getBoolean("result")) {
            ProcessDesign processDesign = new ProcessDesign();
            processExecute.execute(new ProcessExecute(processDesign));
            return result;
        } else {
            return result;
        }

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "success";
    }

}
