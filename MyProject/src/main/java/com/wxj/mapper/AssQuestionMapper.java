package com.wxj.mapper;


import com.wxj.bean.base.AssociatedQuestionRelation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

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


    /**
     * 删除问题关联关系
     * @param standardQuestionId
     * @param companyId
     * @return
     */
    @Update("UPDATE associated_question_relation SET is_del = 1,del_time=Now() " +
            "WHERE standard_question_id=#{standardQuestionId} AND company_id =#{companyId}")
    int delAssQuestion(@Param("standardQuestionId") Integer standardQuestionId,@Param("companyId") Integer companyId);
}
