package cn.nj.springbootone.mapper;

import cn.nj.springbootone.pojo.Item;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Itemmapper {

    @Select("SELECT id,title,sell_point,price,num,barcode,image,cid FROM tb_item")
    List<Item> getItem();
}
