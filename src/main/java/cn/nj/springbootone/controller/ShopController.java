package cn.nj.springbootone.controller;

import cn.nj.springbootone.mapper.Itemmapper;
import cn.nj.springbootone.mapper.ShopMapper;
import cn.nj.springbootone.pojo.Item;
import cn.nj.springbootone.pojo.Shop;
import cn.nj.springbootone.utils.ExcelUtil;
import cn.nj.springbootone.utils.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


import java.util.List;
@RestController
public class ShopController {
    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private Itemmapper itemmapper;

    @RequestMapping("/Listshop")
    @ResponseBody
    public PageInfo<Item> Listshop(@RequestParam(name = "pnum",defaultValue = "1") int pageNum,
                  @RequestParam(name = "psize",defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Item> list = itemmapper.getItem();
        PageInfo<Item> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }

    //导出功能
    @RequestMapping("/export")
    public void execlport(HttpServletRequest req, HttpServletResponse res){
      try {
          String filename="数据统计.xls";
          //查询
          List<Item> list = itemmapper.getItem();
          //查询结果的每一个字段
          String [] string={"id","标题","卖点","价格","库存","商品条形码","图片地址","所属类目"};
          // 将对象转换list
          List<List<String>> listone = new ArrayList<>();
          for (Item m:list) {
              List<String> list2 = new ArrayList<>();
              list2.add(m.getBarcode());
              list2.add(m.getImage());
              list2.add(m.getTitle());
              list2.add(m.getSell_point());
              list2.add(String.valueOf(m.getNum()));
              list2.add(String.valueOf(m.getPrice()));
              list2.add(String.valueOf(m.getId()));
              list2.add(String.valueOf(m.getCid()));
              listone.add(list2);
          }
          ExcelUtil excelUtil = new ExcelUtil();
          excelUtil.exportExcel(req,res,filename,listone,string);
      }catch (Exception e){
        e.printStackTrace();
      }
    }

    /*@RequestMapping("/fy")
    public List  fenye(Integer pn){
        Page page = new Page();
        //查询出的list数据
        List<Item> item = itemmapper.getItem();

        //刚开始的页面为第一页
        if (pn==null){
                page.setCurrentPage(1);
        }else{
            page.setCurrentPage(pn);
        }

        //设置每页数据为十条
        page.setPageSize(20);
        //每页的开始数
        page.setStar((page.getCurrentPage()-1)*page.getPageSize());
        //list的大小
        int count = item.size();
        //设置总页数
        page.setTotalPage(count%10==0 ? count/10 :count/10 +1);
        //对list进行截取
        page.setDataList(item.subList(page.getStar(),count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));

       List list = new ArrayList<>();
       list.add(page);

        return list;


    }*/
}
