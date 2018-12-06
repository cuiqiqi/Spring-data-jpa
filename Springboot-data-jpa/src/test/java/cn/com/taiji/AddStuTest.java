package cn.com.taiji;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.com.taiji.domain.Student;

@SpringBootTest
public class AddStuTest {

	@Autowired
	private Student student;

	@PersistenceContext
	private static EntityManagerFactory factory = null;
	private static EntityManager entityManager = null;
	private static EntityTransaction transaction = null;

	@Before
	public void start() {
		// 1. 创建EntityManagerFactory
		factory = Persistence.createEntityManagerFactory("Springboot-data-jpa");

		// 2. 创建EntityManager
		entityManager = factory.createEntityManager();

		// 3.开启事务
		transaction = entityManager.getTransaction();
		transaction.begin();
	}

	@After
	public void stop() {

		// 5. 提交事务
		transaction.commit();

		// 6. 关闭EntityManager
		entityManager.close();

		// 7. 关闭EntityManagerFactory
		factory.close();
	}
	@Test
	public void testAdd() {
		Student student = new Student();
		student.setName("王小明");
		student.setAge(21);
		entityManager.persist(student);
	}

}
