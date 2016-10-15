package com.wangzhixuan.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by liushaoyang on 2016/10/14.
 */
@Controller
@RequestMapping("/ued")
public class UEditorController {
    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try{
            String exec = new ActionEnter(request,rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        }catch (IOException exp){
            exp.printStackTrace();
        }
    }

}
