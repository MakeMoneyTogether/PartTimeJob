package com.partjob.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 下拉框组件
 * @author chenxing1
 *
 */
@Service
public class DropDownUtil {

	/**
     * 方法名 ：getDropDownMap<BR>
     * 方法说明 ：根据DropDown名称得到相应的Map<BR>
     * 备注 ：无
     * 
     * @param 无
     * @return 无
     * 
     */
    public Map<String, String> getDropDownMap(String dropDownName) {
       return getDropDownMap(dropDownName,null);
    }
    
    public Map<String, String> getDropDownMap(String dropDownName,Object[] params) {
        DropDownItem[] items = getDropDown(dropDownName,params).getList();
        Map<String, String> dropDownMap = new LinkedHashMap<String, String>();
        for (DropDownItem item : items) {
            dropDownMap.put(item.getKey(),item.getValue());
        }
        return dropDownMap;
    }

    /**
     * 方法名 ：getDropDownJSonArray<BR>
     * 方法说明 ：根据DropDown名称得到相应的JSonArray<BR>
     * 备注 ：无
     * 
     * @param
     * @return 无
     * @throws JSONException
     * 
     */
    public JSONArray getDropDownJSonArray(String dropDownName) throws JSONException {
        return getDropDownJSonArray(dropDownName,null);
    }
    
    public JSONArray getDropDownJSonArray(String dropDownName,Object[] params) throws JSONException {
        DropDownItem[] items = getDropDown(dropDownName,params).getList();
        JSONArray array = new JSONArray();
        for (DropDownItem item : items) {
            JSONObject obj = new JSONObject();
            obj.put("id", item.getKey());
            obj.put("text", item.getValue());
            array.add(obj);
        }
        return array;
    }

    /**
     * 翻译中文，根据下拉框中的Key
     * 
     * @param dropDownNm
     * @param dropDownKey
     * @return
     */
    public String getDropDownValue(String dropDownNm, String dropDownKey) {
        return getDropDownValue(dropDownNm, dropDownKey, false);
    }

    /**
     * 获取key对应的描述，
     * 
     * @param dropDownName
     * @param dropDownKey
     * @return key-value 供页面显示
     */
    public String getDropDownValue(String dropDownName, String dropDownKey, boolean showKey) {
        DropDownItem[] items = getDropDown(dropDownName,null).getList();
    	//DropDownItem[] items = DropDownRegistry.getInstance().getDropDown(dropDownName).getList();
        for (DropDownItem item : items) {
            if (item.getKey().equalsIgnoreCase(dropDownKey.trim()) && !StringUtils.isBlank(item.getValue())) {
                if (showKey) {
                    return item.getKey() + "-" + item.getValue();
                }
                return item.getValue();
            }
        }
        return dropDownKey;
    }
    
    /**
     * 根据下拉框值找到key
     * @param dropDownName
     * @param dropDownValue
     * @return
     */
    public String getDropDownKey(String dropDownName, String dropDownValue) {
        DropDownItem[] items = getDropDown(dropDownName,null).getList();
    	//DropDownItem[] items = DropDownRegistry.getInstance().getDropDown(dropDownName).getList();
        for (DropDownItem item : items) {
        	if (item.getValue().trim().equalsIgnoreCase(dropDownValue)) {
        		return item.getKey();
        	}
        }
        return "";
    }
    



    /**
     * 根据下拉框名称获取下拉框实例： 1、从缓存中根据名称获取下拉框 2、如果缓存中还没有下拉框实例，则创建下拉框，并缓存
     *
     * @param dpdnName
     * @return
     */
    private DropDown getDropDown(String dpdnName,Object[] params) {
    	return null;
    }

    /**
     * 根据下拉框名称创建下拉框实例
     *
     * @param dpdnName
     * @return
     */
    private DropDown create(String dpdnName,Object[] params) {
        DropDown dd = null;

        return dd;
    }

    /**
     * 获取静态下拉框
     * @param dpdnName
     * @param params
     * @return
     */
    private DropDown getStaticDrop(String dpdnName,Object[] params){
        DropDown dd = null;

        return dd;
    }

    /**
     * 获取动态下拉框
     * @param dpdnName
     * @param params
     * @return
     */
    private DropDown getDymicDrop(String dpdnName,Object[] params){
        DropDown dd = null;

        return dd;
    }
}





class DropDownItem{

    String key;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }




}


class DropDown {
    String dropName;
    DropDownItem[] list;

    public String getDropName() {
        return dropName;
    }

    public void setDropName(String dropName) {
        this.dropName = dropName;
    }

    public DropDownItem[] getList() {
        return list;
    }

    public void setList(DropDownItem[] list) {
        this.list = list;
    }
}