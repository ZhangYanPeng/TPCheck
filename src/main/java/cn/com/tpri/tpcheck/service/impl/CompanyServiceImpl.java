package cn.com.tpri.tpcheck.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.client.j2se.MatrixToImageConfig;

import cn.com.tpri.tpcheck.dao.impl.CompanyDAOImpl;
import cn.com.tpri.tpcheck.dao.impl.DeviceParamDAOImpl;
import cn.com.tpri.tpcheck.entity.Company;
import cn.com.tpri.tpcheck.entity.Department;
import cn.com.tpri.tpcheck.entity.Device;
import cn.com.tpri.tpcheck.entity.DeviceInfo;
import cn.com.tpri.tpcheck.entity.DeviceParam;
import cn.com.tpri.tpcheck.entity.SuperDevice;
import cn.com.tpri.tpcheck.service.ICompanyService;
import cn.com.tpri.tpcheck.support.Constants;
import cn.com.tpri.tpcheck.support.MatrixToImageWriter;
import cn.com.tpri.tpcheck.support.PageResults;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	CompanyDAOImpl companyDAO;
	@Autowired
	DeviceParamDAOImpl deviceParamDAO;
	
	@Override
	@Transactional
	public PageResults<Company> getByPage(int page) {
		// TODO Auto-generated method stub
		String hql = "from Company";
		String countHql = "select count(*) from Company";
		Object[] values = {};
		return companyDAO.findPageByFetchedHql(hql, countHql, page, Constants.PAGE_SIZE, values);
	}

	@Override
	@Transactional
	public Company load(long id) {
		// TODO Auto-generated method stub
		return companyDAO.get(id);
	}

	@Override
	@Transactional
	public int add(Company company) {
		// TODO Auto-generated method stub
		try {
			companyDAO.save(company);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int edit(Company company) {
		// TODO Auto-generated method stub
		try {
			companyDAO.update(company);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public int delete(Long id) {
		// TODO Auto-generated method stub
		try {
			Company company = companyDAO.get(id);
			if( company.getDepartments().size() >0 ){
				return -1;
			}
			companyDAO.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional
	public List<Company> list() {
		// TODO Auto-generated method stub
		return companyDAO.getListByHQL("from Company", null);
	}

	@Override
	@Transactional
	public int createQR(long id, String basePath) {
		// TODO Auto-generated method stub
		try {
			Company com = companyDAO.get(id);
			String tmpPath = basePath+"/tmp";
			File tdir = new File(tmpPath);
		    if (!tdir.exists()) {// 判断目录是否存在
		        tdir.mkdirs();  //多层目录需要调用mkdirs
		    }
			String path = basePath+"/"+com.getName();
		    File dir = new File(path);
		    if (!dir.exists()) {// 判断目录是否存在
		        dir.mkdirs();  //多层目录需要调用mkdirs
		    }
			String picCName = com.getName();
			for(Department dep : com.getDepartments()){
				String picDName = picCName + dep.getName();
				for(SuperDevice sd : dep.getSuperDevices()){
					String picSDName = picDName + sd.getName();
					for(Device d : sd.getDevices()){
						String picName = picSDName + d.getName() +".jpg";
						String pressText = picSDName + d.getName();
						Map content = new HashMap();
						content.put("id", d.getId());
						String hql = "from DeviceParam where deviceType.id = ? and inQR = 1 order by  level asc, id asc";
						Object[] values = {d.getDeviceType().getId()};
						List<DeviceParam>paramList = deviceParamDAO.getListByHQL(hql, values);
						for(DeviceParam dp : paramList){
							for(DeviceInfo di : d.getDeviceInfos()){
								if(dp.getId() == di.getDeviceParam().getId()){
									content.put(dp.getName(), di.getValue());
								}
							}
						}
						MatrixToImageWriter.create(pressText,(new JSONObject(content)).toString(), path, picName, tmpPath);
					}
				}
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

}
