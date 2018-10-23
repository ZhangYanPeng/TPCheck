package cn.com.tpri.tpcheck.service;

import java.util.Date;
import java.util.List;

import cn.com.tpri.tpcheck.entity.Question;
import cn.com.tpri.tpcheck.support.PageResults;

public interface IQuestionService {
	int add(Question question);
	Question find(long aid, Date date);
	long getUnreadSize(Date date);
	List<Question> getQuestions();
	public PageResults<Question> list(int page);
}
