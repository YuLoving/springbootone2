package cn.nj.springbootone.utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {
    private static final Logger log=Logger.getLogger(ExcelUtil.class);

    /**
     * @param fileName 要导出的文件如：管理列表.xls
     * @param list 要导出的对象 如果查出来的数据是List<对象>形式的 则需要把对象转换为List
     * @param string 表的第一行 如：String[] string={"所属部门","人员信息","执法人员用户名","数据来源"};
     * 			数组内的顺序要和< List >内的位置对应
     * @return
     */
    public void exportExcel(HttpServletRequest req, HttpServletResponse res, String fileName, List<List<String>> list, String[] string){
        WritableWorkbook book=null;
        try {
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");
            res.reset();// 清空输出流
            String agent = req.getHeader("USER-AGENT");
            if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
            {
                fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
            }
            else
            {
                fileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
            }
            //不能用用中文设置 filename，会出错
            res.setHeader("Content-disposition", "attachment; filename="+fileName);// 设定输出文件头
            res.setContentType("application/msexcel");// 定义输出类型
            book= Workbook.createWorkbook(res.getOutputStream());
            WritableSheet sheet=book.createSheet(fileName, 0);
            //设置第一行为表头
            for(int i=0;i<string.length;i++){
                sheet.addCell(new Label(i,0,string[i]));
            }
            //将数据追加
            for(int i=0;i<list.size();i++){
                for(int j=0;j<list.get(i).size();j++){
                    sheet.addCell(new Label(j,i+1,list.get(i).get(j)==null?" ":list.get(i).get(j).toString()));
                }
            }
            book.write();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
            log.info("没有要导出的数据!");
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("没有要导出的数据!");
                }
            }
        }
        return ;
    }
}



