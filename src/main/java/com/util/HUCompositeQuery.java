package com.util;


import com.cinema.EmpVO;
import com.cinema.FuncVO;
import com.cinema.JobVO;
import com.cinema.PermissionVO;
import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HUCompositeQuery {

    public static Predicate getPredacate(CriteriaBuilder builder, Root<EmpVO> root, String columnName, String value) {

        Predicate predicate = null;

        //Id、name、

        if ("empId".equals(columnName))
            predicate = builder.equal(root.get(columnName), Integer.valueOf(value));
        else if ("empName".equals(columnName) || "empEmail".equals(columnName) || "empStatus".equals(columnName))
            predicate = builder.like(root.get(columnName), "%" + value + "%");
        else if ("hiredate".equals(columnName)) // 用於date
            predicate = builder.equal(root.get(columnName), java.sql.Date.valueOf(value));
        else if ("jobId".equals(columnName)) {
            JobVO jobVO = new JobVO();
            jobVO.setJobId(Integer.valueOf(value));
            predicate = builder.equal(root.get("jobVO"), jobVO);
        }

        return predicate;

    }

    @SuppressWarnings("unchecked")
    public static List<EmpVO> getAllC(Map<String,String[]>map, Session session){

        Transaction tx = session.beginTransaction();
        List<EmpVO> list = null;

        try {
            // 【●創建 CriteriaBuilder】
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // 【●創建 CriteriaQuery】
            CriteriaQuery<EmpVO> criteriaQuery = builder.createQuery(EmpVO.class);
            // 【●創建 Root】
            Root<EmpVO> root = criteriaQuery.from(EmpVO.class);

            Join<EmpVO, FuncVO> funcJoin = root.join("funcVO", JoinType.INNER);

            List<Predicate> predicateList = new ArrayList<Predicate>();

            Set<String> keys = map.keySet();
            int count = 0;
            for (String key : keys) {
                String value = map.get(key)[0];
                if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
                    count++;
                    predicateList.add(getPredacate(builder, root, key, value.trim()));
                    System.out.println("有送出查詢資料的欄位數count = " + count);
                }
            }

            System.out.println("predicateList.size()="+predicateList.size());
            criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
            criteriaQuery.orderBy(builder.asc(root.get("empId")));
            // 【●最後完成創建 javax.persistence.Query●】
            Query query = session.createQuery(criteriaQuery); //javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面
            list = query.getResultList();

            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null)
                tx.rollback();
            throw ex; // System.out.println(ex.getMessage());
        } finally {
            session.close();
            // HibernateUtil.getSessionFactory().close();
        }

        return list;
        }

    }


