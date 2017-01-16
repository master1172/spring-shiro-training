package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.PeopleJob;
import com.wangzhixuan.model.PeopleRank;
import com.wangzhixuan.model.PeopleTransfer;
import com.wangzhixuan.service.PeopleRankService;
import com.wangzhixuan.service.PeopleTransferService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.PeopleJobVo;
import com.wangzhixuan.vo.PeopleRankVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/people/rank")
public class PeopleRankController extends BaseController{
    private static Logger LOGGER = LoggerFactory.getLogger(PeopleRankController.class);
    @Autowired
    private PeopleRankService peopleRankService;

    @Autowired
    private PeopleTransferService peopleTransferService;

    /**
     * 人员薪级页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/peopleRank/peopleRank";
    }

    /**
     * 人员薪级列表
     * @param request
     * @param peoplerankvo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value="/dataGrid", method=RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(HttpServletRequest request, PeopleRankVo peoplerankvo, Integer page, Integer rows, String sort, String order){
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String,Object> condition = PeopleRankVo.CreateCondition(peoplerankvo);
        pageInfo.setCondition(condition);
        peopleRankService.findDataGrid(pageInfo, request);

        return pageInfo;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Result add(PeopleRankVo peopleRankVo, @RequestParam(value="fileName",required=false)CommonsMultipartFile file) {
        Result result = new Result();
        try {

            peopleRankService.addPeopleRank(peopleRankVo,file);
            result.setSuccess(true);
            result.setMsg("添加成功");
            return result;
        } catch (Exception e) {
            LOGGER.error("添加用户失败：{}", e);
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/editPage")
    public String editPage(Long id, Model model){
        PeopleRankVo peopleRankVo = peopleRankService.findPeopleRankVoById(id);
        model.addAttribute("peopleJobVo",peopleRankVo);
        return "/admin/people/peopleEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(PeopleRankVo peoplerankvo, @RequestParam(value="fileName",required=false)CommonsMultipartFile file){
        Result result = new Result();
        try{
            peopleRankService.updatePeopleRank(peoplerankvo,file);
            result.setSuccess(true);
            result.setMsg("修改成功!");
            return result;
        }catch(Exception e){
            LOGGER.error("修改人员失败：{}",e);
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
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        }catch(RuntimeException e){
            LOGGER.error("删除人员失败：{}",e);
            result.setMsg(e.getMessage());
            return result;
        }
    }


}
