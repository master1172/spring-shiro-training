package com.wangzhixuan.mapper;

import com.wangzhixuan.model.Recruit;
import com.wangzhixuan.model.Training;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.RecruitVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by sterm on 2017/2/14.
 */
public interface RecruitMapper {

    List findPeoplePageCondition(PageInfo pageInfo);

    int findPeoplePageCount(PageInfo pageInfo);

    Recruit findRecruitById(Integer id);

    int insert(Recruit recruit);



    void update(Recruit recruit);

    int delete(Integer id);

    int batchDeleteByIds(String[] idList);

    int insertByImport(List<Recruit> list);

    List selectRecruitVoByIds(String[] idList);

    RecruitVo findRecruitVoById(Integer id);
}
