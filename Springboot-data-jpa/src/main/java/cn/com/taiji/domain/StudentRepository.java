package cn.com.taiji.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student>{
	
	
	Student findByName(String name); 
      // 构造查询方法  通过学生的姓名和年龄查询出学生
	Student findByNameAndAge(String name,Integer age);
	  // 构造查询方法  通过学生的姓名、id和年龄查询出学生
	Student findByNameAndIdAndAge(String name,Integer id,Integer age);

	@Query(value = "SELECT s FROM Student s WHERE s.name = ?1")
	Student findByname1(String name);
	
	@Modifying
	@Query(value = "DELETE s FROM Student s WHERE s.id = ?1")
	boolean deleteById(Integer id);
	
	  
}
