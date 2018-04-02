package com.wxj.mapper;


import com.wxj.bean.base.SimilarQuestionRelation;
import com.wxj.bean.base.StandardQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Insert("INSERT INTO similar_question_relation (company_id,standard_question_id,title,create_time) VALUES " +
            "(#{companyId},#{standardQuestionId},#{title},Now())")
    int insertSimQuestion(SimilarQuestionRelation similarQuestionRelation);

}
