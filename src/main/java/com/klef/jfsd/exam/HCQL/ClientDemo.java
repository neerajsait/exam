package com.klef.jfsd.exam.HCQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class ClientDemo {
	public static void main(String args [])
	{
	ClientDemo client = new ClientDemo();
	//client.addcustomer();
	client.functions();
	
	
	}
	public void addcustomer() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction t = session.beginTransaction();
		
		Customer customer = new Customer();
		customer.setName("sai");
		customer.setAge(31);
		customer.setEmail("sai@gmail.com");
		customer.setLocation("chennai");
		
		session.persist(customer);
		t.commit();
	
		System.out.println("Customer Added Successfully");
		
		session.close();
		sf.close();
		
	}
	public void functions() {
		Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);
        //cq.select(root).where(cb.lessThan(root.get("age"), 30));
        //cq.select(root).where(cb.equal(root.get("age"), 20));
        //cq.select(root).where(cb.notEqual(root.get("age"), 20));
        //cq.select(root).where(cb.greaterThan(root.get("age"), 20));
        //cq.select(root).where(cb.like(root.get("location"), "vijayawada"));
		cq.select(root).where(cb.between(root.get("age"), 20, 50));
        System.out.println("*****Customers Objects with different comparison criteria*****");
	    List<Customer> Customers =  session.createQuery(cq).getResultList();
	    System.out.println("Customers Count="+Customers.size());
	    for(Customer s : Customers)
	    {
	     
	    System.out.println(s.toString());
	    }
        session.close();
        sf.close();
		
	}

}
