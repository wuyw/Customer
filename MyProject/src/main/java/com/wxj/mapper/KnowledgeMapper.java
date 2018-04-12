package com.wxj.mapper;


import com.wxj.bean.base.KnowledgePoint;
import com.wxj.bean.dto.KnowledgePointDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KnowledgeMapper {

    /**
     * 新增知识点
     * @param knowledgePoint
     * @return
     */
    @Insert("INSERT INTO knowledge_point (company_id,title,create_time,start_time,end_time,content,`status`,is_del) VALUES (#{companyId},#{title},Now(),#{startTime},#{endTime},#{content},#{status},0)")
    int insertKnowledge(KnowledgePoint knowledgePoint);

    /**
     * 更新知识点
     * @param knowledgePoint
     * @return
     */
    @Update("UPDATE knowledge_point SET title = #{title},start_time=#{startTime},end_time=#{endTime},content=#{content},status=#{status},edit_time=Now() " +
            "WHERE id = #{id}")
    int updateKnowledge(KnowledgePoint knowledgePoint);

    /**
     * 列表查询知识点
     * @param companyId
     * @param start
     * @param end
     * @return
     */
    @Select("SELECT id,company_id AS companyId,title,create_time AS createTime,`status`,start_time AS startTime,end_time AS endTime,content,edit_time AS editTime " +
            "FROM knowledge_point " +
            "WHERE is_del = 0 AND company_id = #{companyId} LIMIT #{start},#{end}")
    List<KnowledgePoint> getPointList(@Param("companyId")Integer companyId,@Param("start")Integer start,@Param("end")Integer end);


    /**
     * 获取知识点数量
     * @param companyId
     * @return
     */
    @Select("SELECT COUNT(*) FROM knowledge_point WHERE is_del = 0 AND company_id = #{companyId}")
    int getPointCount(@Param("companyId")Integer companyId);

    /**
     * 获取模糊查询知识点数量
     * @param companyId
     * @return
     */
    @Select("<script>" +
            "SELECT COUNT(*) FROM knowledge_point WHERE is_del = 0 AND company_id = #{companyId} " +
            "<if test ='title!=null'> " +
            "AND title LIKE CONCAT('%','${title}','%' )  " +
            "</if> " +
            "<if test ='content!=null'> " +
            "AND title content CONCAT('%','${content}','%' )  " +
            "</if> " +
            "</script>")
    int getPointCountByParams(@Param("companyId")Integer companyId);


    /**
     * 模糊查询
     * @return
     */
    @Select("<script>" +
            "SELECT id,company_id AS companyId,title,create_time AS createTime,`status`,start_time AS startTime,end_time AS endTime,content,edit_time AS editTime " +
            "FROM knowledge_point " +
            "WHERE is_del = 0 AND company_id = #{companyId} " +
            "<if test ='title!=null'> " +
            "AND title LIKE CONCAT('%','${title}','%' )  " +
            "</if> " +
            "<if test ='content!=null'> " +
            "AND title content CONCAT('%','${content}','%' )  " +
            "</if> " +
            "LIMIT #{start},#{end}" +
            "</script>")
    List<KnowledgePoint> getPointListByParams(KnowledgePoint knowledgePoint,@Param("start")Integer start,@Param("end")Integer end);

    /**
     * 通过ID查询知识点
     * @param id
     * @return
     */
    @Select("SELECT id,company_id AS companyId,title,create_time AS createTime,`status`,start_time AS startTime,end_time AS endTime,content,edit_time AS editTime " +
            "FROM knowledge_point " +
            "WHERE id = #{id} AND is_del = 0")
    KnowledgePoint getPointById(@Param("id") Integer id);


    /**
     * 删除知识点
     * @param id
     * @return
     */
    @Select("UPDATE knowledge_point SET is_del = 1 WHERE id = #{id}")
    int delPoint(@Param("id") Integer id);


    /**
     * 通过title查询知识点
     * @param title
     * @return
     */
    @Select("SELECT id,company_id AS companyId,title,create_time AS createTime,`status`,start_time AS startTime,end_time AS endTime,content,edit_time AS editTime " +
            "FROM knowledge_point " +
            "WHERE title = #{title} AND is_del = 0 AND company_id = #{companyId}")
    KnowledgePoint getPointByTitle(@Param("title") String title,@Param("companyId") Integer companyId);
}
