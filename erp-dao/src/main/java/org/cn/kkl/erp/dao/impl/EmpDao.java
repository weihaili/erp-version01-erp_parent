package org.cn.kkl.erp.dao.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.entity.Emp;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class EmpDao extends BaseDao<Emp> implements IEmpDao {
	
	
	/**
	 * @param paramDep
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Emp emp1,Emp emp2,Object param) {
		
		DetachedCriteria criteria =DetachedCriteria.forClass(Emp.class);
		if (null!=emp1) {
			if (StringUtils.isNotBlank(emp1.getUsername())) {
				criteria.add(Restrictions.like("username", emp1.getUsername(),MatchMode.ANYWHERE));
			}
			if (StringUtils.isNoneBlank(emp1.getPwd())) {
				criteria.add(Restrictions.like("pwd", emp1.getPwd(), MatchMode.EXACT));
			}
			if (StringUtils.isNotBlank(emp1.getName())) {
				criteria.add(Restrictions.like("name", emp1.getName(),MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(emp1.getEmail())) {
				criteria.add(Restrictions.like("email", emp1.getEmail(),MatchMode.START));
			}
			if (StringUtils.isNotBlank(emp1.getTele())) {
				criteria.add(Restrictions.like("tele", emp1.getTele(),MatchMode.START));
			}
			if (StringUtils.isNotBlank(emp1.getAddress())) {
				criteria.add(Restrictions.like("address", emp1.getAddress(),MatchMode.START));
			}
			if (null!=emp1.getBirthday()) {
				criteria.add(Restrictions.ge("birthday", emp1.getBirthday()));
			}
			if (null!=emp1.getDep() && null!=emp1.getDep().getUuid()) {
				criteria.add(Restrictions.eq("dep", emp1.getDep()));
			}
			if (StringUtils.isNotBlank(String.valueOf(emp1.getGender()).trim())) {
				criteria.add(Restrictions.eq("gender", emp1.getGender()));
				/*if ("a".equals(String.valueOf(emp1.getGender()))) {
					Map<String, Character> genderMap = new HashMap<>();
					genderMap.put("male", '1');
					genderMap.put("female", '0');
					criteria.add(Restrictions.eq("gender", '1'));
					criteria.add(Restrictions.eq("gender", '0'));
				}*/
			}
			
		}
		if (null!=emp2) {
			if (null!=emp2.getBirthday()) {
				criteria.add(Restrictions.le("birthday", emp2.getBirthday()));
			}
		}
		return criteria;
	}

	/* 
	 * user login verification
	 */
	@Override
	public Emp findByUsernameAndPwd(String username, String password) {
		String sql="FROM Emp where username=? and pwd=? ";
		List<Emp> list = (List<Emp>) this.getHibernateTemplate().find(sql, username,password);
		if (null!=list && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	/* 
	 * update password by uuid
	 */
	@Override
	public void updatePwd(Long uuid,String newPwd) {
		String sql="UPDATE Emp SET PWD = ? WHERE UUID=? ";
		this.getHibernateTemplate().bulkUpdate(sql, newPwd,uuid);
	}

	
	
	
	
}
