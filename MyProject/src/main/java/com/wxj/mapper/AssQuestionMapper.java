package com.wxj.mapper;


import com.wxj.bean.base.AssociatedQuestionRelation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssQuestionMapper {

    /**
     * 新增关系
     * @param associatedQuestionRelation
     * @return
     */
    @Insert("INSERT INTO associated_question_relation (standard_question_id,associated_question_id,company_id,create_time) " +
            "VALUES(#{standardQuestionId},#{similarQuestionId},#{companyId},Now())")
    int insertAssQuestion(AssociatedQuestionRelation associatedQuestionRelation);

}
