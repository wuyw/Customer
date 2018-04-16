package com.wxj.mapper;


import com.wxj.bean.base.SimilarQuestionRelation;
import com.wxj.bean.base.StandardQuestion;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SimilarQuestionMapper {

    /**
     * 通过标题查询
     * @param title
     * @return
     */
    @Select("SELECT id,company_id AS companyId,standard_question_id AS standardQuestionId,title,create_time AS createTime FROM similar_question_relation " +
            "WHERE is_del = 0 AND title = #{title} AND company_id = #{companyId}")
    SimilarQuestionRelation getSimQuestionByTitle(@Param("title") String title,@Param("companyId") Integer companyId);

    /**
     * 添加
     * @param similarQuestionRelation
     * @return
     */
    @Insert("INSERT INTO similar_question_relation (company_id,standard_question_id,title,create_time,is_del) VALUES " +
            "(#{companyId},#{standardQuestionId},#{title},Now(),0)")
    int insertSimQuestion(SimilarQuestionRelation similarQuestionRelation);

    /**
     * 删除标准问题对应的相似问题
     * @param standardQuestionId
     * @param companyId
     * @return
     */
    @Update("UPDATE similar_question_relation SET is_del = 0,del_time = Now() " +
            "WHERE standard_question_id = #{standardQuestionId} AND company_id  = #{companyID}")
    int delSimQuestion(@Param("standardQuestionId") Integer standardQuestionId,@Param("companyId") Integer companyId);

}
