package com.wxj.mapper;


import com.wxj.bean.base.StandardQuestion;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StandardQuestionMapper {

    /**
     * 添加问题
     * @param standardQuestion
     * @return
     */
    @Insert("INSERT INTO standard_question (company_id,knowledge_point_id,create_time,start_time,end_time,status,title,answer,is_del) VALUES " +
            "(#{companyId},#{knowledgePointId},Now(),#{startTime},#{endTime},#{status},#{title},#{answer},0" +
            ")")
    int insertStaQuestion(StandardQuestion standardQuestion);

    /**
     * 通过标题获取问题
     * @param title
     * @param companyId
     * @return
     */
    @Select("SELECT id,company_id AS companyId,knowledge_point_id AS knowledgePointId,create_time AS createTime,start_time AS startTime,end_time AS endTime,status,title,answer " +
            "FROM standard_question " +
            "WHERE company_id = #{companyId} AND title = #{title} AND is_del  = 0")
    StandardQuestion getStaQuestionByTitle(@Param("title") String title,@Param("companyId") Integer companyId);


    /**
     * 分页获取问题列表
     * @param companyId
     * @param start
     * @param end
     * @return
     */
    @Select("SELECT id,company_id AS companyId,knowledge_point_id AS knowledgePointId,create_time AS createTime,start_time AS startTime,end_time AS endTime,status,title,answer " +
            "FROM standard_question " +
            "WHERE company_id = #{companyId} AND is_del = 0 " +
            "LIMIT #{start},#{end}")
    List<StandardQuestion> getStaQuestionList(@Param("companyId") Integer companyId,@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 删除问题
     * @param id
     * @param companyId
     * @return
     */
    @Update("UPDATE standard_question SET is_del = 1,del_time = Now() " +
            "WHERE id = #{id} AND company_id = #{companyId}")
    int delStaQuestion(@Param("id") Integer id,@Param("companyId") Integer companyId);

    /**
     * 获取问题总数
     * @param companyId
     * @return
     */
    @Select("SELECT COUNT(*) FROM standard_question WHERE company_id = #{companyId} AND is_del = 0")
    int getQuestionCount(@Param("companyId") Integer companyId);

    /**
     * 模糊查询获取列表（分页）
     * @param companyId
     * @param start
     * @param end
     * @return
     */
    @Select("SELECT id,company_id AS companyId,knowledge_point_id AS knowledgePointId,create_time AS createTime,start_time AS startTime,end_time AS endTime,status,title,answer " +
            "FROM standard_question " +
            "WHERE company_id = #{companyId} AND is_del = 0 AND title LIKE CONCAT('%','${keywords}','%') " +
            "LIMIT #{start},#{end}")
    List<StandardQuestion> getStaQuestionListByKeywords(@Param("keywords") String keywords,@Param("companyId") Integer companyId,@Param("start") Integer start,@Param("end") Integer end);

    /**
     * 获取问题总数
     * @param companyId
     * @return
     */
    @Select("SELECT COUNT(*) FROM standard_question WHERE company_id = #{companyId} AND is_del = 0 AND title LIKE CONCAT('%','${keywords}','%')")
    int getQuestionCountByKeywords(@Param("keywords") String keywords,@Param("companyId") Integer companyId);
}
