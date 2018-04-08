package net.sf.excelutils.demo.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.excelutils.ExcelUtils;
import net.sf.excelutils.demo.bo.Model;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ExcelReportAction extends Action {

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      javax.servlet.http.HttpServletRequest request,
      javax.servlet.http.HttpServletResponse response)
      throws java.lang.Exception {  
    // 准备数据1
    Model model = new Model();
    model.setUser("aaa");
    model.setName("客数码");
    model.setQty(123.234);
    model.setCount(0);
    model.setField1("test");
    model.setYear("2001");

    List details = new ArrayList();
    for (int i = 1; i < 4; i++) {
      Model model1 = new Model();
      model1.setUser("bbbcadff"+(int)(i/2));
      model1.setName("数码客运"+(int)(i/2));
      model1.setQty(909.234+i);
      model1.setCount(i); 
      model1.setYear("200"+(int)(i/3));
      details.add(model1);
    }
    model.setChildren(details);

    Map maps = new LinkedHashMap();
    maps.put("key0", "1");
    maps.put("key1", "数学");
    maps.put("key2", "英语");
    maps.put("key3", "政治");
    maps.put("key4", "历史");  
    
    List keys = new ArrayList();
    keys.add("key4");
    keys.add("key2");

    List list = new ArrayList();
    Map map0 = new LinkedHashMap();
    map0.put("key0", new Integer(90));
    map0.put("key1", new Integer(92));
    map0.put("key2", new Integer(89));
    map0.put("key3", new Integer(69));    
    map0.put("key4", new Integer(72));    
    list.add(map0);
    Map map1 = new LinkedHashMap();
    map1.put("key0", new Integer(95));
    map1.put("key1", new Integer(90));
    map1.put("key2", new Double(80.03));
    map1.put("key3", new Integer(64));
    map1.put("key4", new Integer(77));    
    list.add(map1);
    
    Map map111 = new LinkedHashMap();
    map111.put("aaa", "标题");
    
    List aList = new ArrayList();
    aList.add("中国");
    aList.add("美国");
    aList.add(map1);    
    aList.add("俄罗斯");

    ExcelUtils.addValue("printDate", getCurrentDate("yyyy年MM月dd日"));
    ExcelUtils.addValue("field", "name");
    ExcelUtils.addValue("model", model);
    ExcelUtils.addValue("maps", maps);
    ExcelUtils.addValue("keys", keys);
    ExcelUtils.addValue("list", list);
    ExcelUtils.addValue("index", new Integer(1));
    ExcelUtils.addValue("key", "key0");
    ExcelUtils.addValue("where", "数码客运");
    ExcelUtils.addValue("dd", "Date");
    ExcelUtils.addValue("patten", "yyyy-MM-dd");
    ExcelUtils.addValue("width", "2");
    ExcelUtils.addValue("width1", new Integer(11));
    ExcelUtils.addValue("title", map111);
    ExcelUtils.addValue("array", new String[] {"北京","上海","广州"});
    ExcelUtils.addValue("array_int", new int[] {22,33,44});
    ExcelUtils.addValue("alist", aList);
    ExcelUtils.addService("service", this);
    ExcelUtils.addService("stati", ExcelReportAction.class);
    
    String config = "/WEB-INF/xls/demo.xls";

    response.reset();
    response.setContentType("application/vnd.ms-excel");
    // 输出Excel
    ExcelUtils.export(getServlet().getServletContext(), 
        config, response.getOutputStream());
    return null;
  }

  public String getCurrentDate(String pattern) {
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    return format.format(new Date());
  }
  
  public String getCurrentDate(String pattern, int aaa) {
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    return format.format(new Date())+aaa;
  }
  public static Model getMyModel() {
    Model m = new Model();
    m.setName("aaabbb");
    return m;
  }
  public static Model getMyModel(String a) {
    Model m = new Model();
    m.setName("aaabbb"+a);
    return m;
  }
}
