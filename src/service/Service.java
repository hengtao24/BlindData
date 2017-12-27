package service;

import java.util.List;

public interface Service {

    List<Object> getList();//获取列表

    int insert(Object[] params);//插入一条

    int modify(Object[] params);//修改

    int delete(Object[] params);//删除

    int insert(String sql);
}
