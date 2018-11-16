package cn.nj.springbootone.mapper;

import cn.nj.springbootone.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopMapper {
    @Select("select *from t_product")
    List<Shop> findall();
}
