/**
 * 版权声明：厦门中软海晟信息技术有限公司 版权所有 违者必究
 * 日    期：2019-02-16
 */
package com.young.rabbitmq.demo1.base;

import java.util.List;
import java.util.Map;

/**
 * 通用MAPPER
 *
 * @author 钟治明
 * @version 1.0
 */
public interface BaseMapper<E> {

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    public int insert(E entity);

    /**
     * 根据主键删除
     *
     * @param objId
     * @return
     */
    public int delByPrimaryKey(String objId);

    /**
     * 根据查询条件删除
     *
     * @param param
     * @return
     */
    public int delByParam(Map<String, Object> param);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    public int edit(E entity);

    /**
     * 根据主键更新不为空的字段
     *
     * @param entity
     * @return
     */
    public int editByIdSelective(E entity);

    /**
     * 根据主键查询对象
     *
     * @param objId
     * @return
     */
    public E selectByPrimaryKey(String objId);

    /**
     * 根据多个条件查询一个对象
     * 多主键情况
     * @param filters
     * @return
     */
    public E selectOneByCondition(Map<String, Object> filters);

    /**
     * 根据条件查询结果集,包括传入分页参数
     *
     * @param param
     * @return
     */
    public List<E> selectList(Map<String, Object> param);

    /**
     * 根据条件查询结果集数量
     *
     * @param param
     * @return
     */
    public int countList(Map<String, Object> param);



}
