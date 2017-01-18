package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.service.PeopleJobService;
import com.wangzhixuan.service.PeopleTransferService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleJobVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangwk on 2017/1/15.
 */
@Controller
@RequestMapping("/people/job")
public class PeopleJobController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleJobController.class);

    @Autowired
    private PeopleJobService peopleJobService;

    /**
     * 人员职级页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/peopleJob/peopleJob";
    }

    /**
     * 人员职级列表
     * @param request
     * @param peoplejobvo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */

    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, PeopleJobVo peoplejobvo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = PeopleJobVo.CreateCondition(peoplejobvo);
        pageInfo.setCondition(condition);
        peopleJobService.findDataGrid(pageInfo, request);

        return pageInfo;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleJobVo peopleJobVo) {
        Result result = new Result();
        try {

            peopleJobService.addPeopleJob(peopleJobVo);
            result.setSuccess(true);
            result.setMsg("添加职级成功");
            return result;
        } catch (Exception e) {
            LOGGER.error("添加职级失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleJobVo peoplejobvo){
        Result result = new Result();
        try{
            peopleJobService.updatePeopleJob(peoplejobvo);
            result.setSuccess(true);
            result.setMsg("修改职级成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("修改职级失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id){
        Result result = new Result();
        try{
            peopleJobService.deletePeopleJobById(id);
            result.setMsg("删除职级成功！");
            result.setSuccess(true);
            return result;
        }catch(RuntimeException e){
            LOGGER.error("删除职级失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
