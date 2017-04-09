package com.partjob.controller;

import com.partjob.constant.CommonCanstant;
import com.partjob.model.MchntInfo;
import com.partjob.service.MchntService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by MPJ on 17/4/8.
 */
@Controller
@RequestMapping(value = "mchnt")
public class MchntController extends BaseController{
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MchntService mchntService;
    @RequestMapping(value = { "register" })
    @ResponseBody
    public Object register(MchntInfo mchntInfo,
                           @RequestParam(value="password")String password,
                           @RequestParam(value="verPassword")String verPassword){

        if(!password.equals(verPassword)){
            return CommonCanstant.FAIL;
        }
        try{
            mchntService.saveMchnt(mchntInfo,password);
        }catch(Exception e){
            return CommonCanstant.FAIL;
        }

        return CommonCanstant.SUCCESS;
    }

}
