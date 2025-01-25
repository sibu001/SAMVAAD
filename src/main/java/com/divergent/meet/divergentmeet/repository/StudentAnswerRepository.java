package com.divergent.meet.divergentmeet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.divergent.meet.divergentmeet.model.StudentAnswer;

@Repository
public interface StudentAnswerRepository  extends JpaRepository<StudentAnswer, Long>  {

	@Query("select sa.id as id , sa.source_code as source_code , concat(s.name) as name ,sub.name as lang   ,q.question as question     from StudentAnswer sa left join sa.student s left join sa.question q left join q.session ses left join ses.sessionMaster sm left join sm.subject sub   where sa.question.id=:questionId ")
	List<Map<String,Object>> getAllByQuestion(@Param("questionId") Long questionId);


	@Query("select stc.status as status , stc.stdout as stdout , stc.stderr as stderr , stc.memory as memory , stc.compile_output as compile_output"
			+ " , stc.time as time , stc.sandbox_message as sandbox_message , tc.input as input , tc.stdin as stdin ,tc.compiler_options as compiler_options  "
			+ " from  StudentTestCase stc left join stc.testCase tc left  join stc.studentAnswer sa left  join sa.student s where s.id=:id  ")
	List<Map<String,Object>> getTestCaseById(@Param("id") Long id);


	@Query("select sa.id as id , sa.source_code as source_code , concat(s.name) as name , q.id as questionId, q.question as question,  sub.name as lang     from StudentAnswer sa left join sa.student s left join sa.question q left join q.session ses left join ses.sessionMaster sm left join sm.subject sub   where s.id=:studentId and ses.id=:sessionId ")
	List<Map<String,Object>> getByStudentAndSession(@RequestParam("studentId") Long studentId,@RequestParam("sessionId") Long sessionId);
	

	
	

}
