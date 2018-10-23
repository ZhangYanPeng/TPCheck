package cn.com.tpri.tpcheck.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tpri.tpcheck.dao.impl.PictureDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.QuestionDAOImpl;
import cn.com.tpri.tpcheck.entity.DeviceCheckRecord;
import cn.com.tpri.tpcheck.entity.Picture;
import cn.com.tpri.tpcheck.entity.Question;
import cn.com.tpri.tpcheck.service.IQuestionService;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	QuestionDAOImpl questionDAO;
	@Autowired
	PictureDAOImpl pictureDAO;
	
	@Override
	@Transactional
	public int add(Question question) {
		// TODO Auto-generated method stub
		try {
			questionDAO.save(question);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	@Transactional
	public Question find(long aid, Date date) {
		// TODO Auto-generated method stub
		String hql = "from Question where account.id = ? and date = ?";
		Object[] values = {aid, date};
		return questionDAO.getByHQL(hql, values);
	}

	@Override
	@Transactional
	public long getUnreadSize(Date date) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Question where date > ?";
		Object[] values = {date};
		return questionDAO.countByHql(hql, values);
	}

	@Override
	@Transactional
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		String hql = "from Question order by date desc";
		Object[] values = null;
		List<Question> ques = questionDAO.getListByHQL(hql, values);
		for( Question q : ques){
			if(q.getPictures() == null || q.getPictures().size() == 0 && q.getImgStr() != null && !q.getImgStr().equals("") ){
				String[] pics = q.getImgStr().split(";");
				for (int i=1; i<pics.length;i++){
					String hqlString = "from Picture where name = ?";
					Object[] val = {pics[i]};
					Picture pic = pictureDAO.getByHQL(hqlString, val);
					pic.setQuestion(q);;
					pictureDAO.update(pic);
				}
			}
		}
		return questionDAO.getListByHQL(hql, values);
	}

	@Override
	@Transactional
	public PageResults<Question> list(int page) {
		// TODO Auto-generated method stub
		String hql = "from Question order by date desc";
		String countHql = "select count(*) from Question";
		Object[] values = null;
		PageResults<Question> ques = questionDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
		for( Question q : ques.getResults()){
			if(q.getPictures() == null || q.getPictures().size() == 0 && q.getImgStr() != null && !q.getImgStr().equals("") ){
				String[] pics = q.getImgStr().split(";");
				for (int i=1; i<pics.length;i++){
					String hqlString = "from Picture where name = ?";
					Object[] val = {pics[i]};
					Picture pic = pictureDAO.getByHQL(hqlString, val);
					pic.setQuestion(q);
					pictureDAO.update(pic);
				}
			}
		}
		return questionDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}
}
