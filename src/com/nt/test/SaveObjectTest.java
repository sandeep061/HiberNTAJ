package com.nt.test;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest {

	public static void main(String[] args) {
	Configuration cfg=null;
	SessionFactory factory=null;
	Session ses=null;
	    boolean flag=false;
	    int idval=0;
	org.hibernate.Transaction tx=null;
	//Activate Hibernate framework
	cfg=new Configuration();
	//linking cfgs file
	cfg.configure("/com/nt/cfgs/hibernate.cfgs.xml");
	
	//build the session factory
	factory=cfg.buildSessionFactory();
	
	//open session
	ses=factory.openSession();
	
	Product p=new Product();
	p.setPid(67);p.setPname("chairsssss");p.setPrice(34000f);p.setQty(4.0f);
	
	try {
	   tx=ses.beginTransaction();//internally call con.setAutocommite(false)
	 idval=  (int) ses.save(p);
	 System.out.println("idval:"+idval);
	   flag=true;
	}catch (Exception e) {
	e.printStackTrace();
	flag=false;
	}
	finally {
		
	if(flag==true) {
		tx.commit();//internally calles con.commit
		System.out.println("object is saved");
	}else {
		tx.rollback();//internally calles con.rollback
	}
	ses.close();
	factory.close();
	}
	
	}

}

