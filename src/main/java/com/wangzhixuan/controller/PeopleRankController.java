package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.service.PeopleRankService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleJobVo;
import com.wangzhixuan.vo.PeopleRankVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wangwk on 2017/1/15.
 */
@Controller
@RequestMapping("/peopleRank")
public class PeopleRankController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleRankController.class);
    @Autowired
    private PeopleRankService peopleRankService;

    /**
     * 人员薪级页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/peopleRank/people";
    }

    /**
     * 人员薪级列表
     * @param request
     * @param peopleRankvo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, PeopleRankVo peopleRankvo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = PeopleRankVo.createCondition(peopleRankvo);
        pageInfo.setCondition(condition);
        peopleRankService.findDataGrid(pageInfo, request);

        return pageInfo;
    }
    @RequestMapping(value="/addPage", method=RequestMethod.GET)
    public String addPage(){
        return "admin/peopleRank/peopleAdd";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleRankVo peopleRankVo) {
        Result result = new Result();
        try {

            peopleRankService.addPeopleRank(peopleRankVo);
            result.setSuccess(true);
            result.setMsg("添加薪级成功");
            return result;
        } catch (Exception e) {
            LOGGER.error("添加薪级失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }
    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        PeopleRankVo peopleRankVo = peopleRankService.findPeopleRankVoById(id);
        model.addAttribute("peopleRankVo",peopleRankVo);
        return "/admin/peopleRank/peopleRankEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleRankVo peoplerankvo){
        Result result = new Result();
        try{
            peopleRankService.updatePeopleRank(peoplerankvo);
            result.setSuccess(true);
            result.setMsg("修改薪级成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("修改薪级失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id){
        Result result = new Result();
        try{
            peopleRankService.deletePeopleRankById(id);
            result.setMsg("删除薪级成功！");
            result.setSuccess(true);
            return result;
        }catch(RuntimeException e){
            LOGGER.error("删除薪级失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }


}
