package com.wxj.mapper;


import com.wxj.bean.base.StandardQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StandardQuestionMapper {

    /**
     * 添加问题
     * @param standardQuestion
     * @return
     */
    @Insert("INSERT INTO standard_question (company_id,knowledge_point_id,create_time,start_time,end_time,status,title,answer) VALUES " +
            "(#{companyId},#{knowledgePointId},Now(),#{startTime},#{endTime},#{status},#{title},#{answer}" +
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
            "WHERE company_id = #{comapnyId} AND title = #{title} ")
    StandardQuestion getStaQuestionByTitle(@Param("title") String title,@Param("companyId") Integer companyId);

}
