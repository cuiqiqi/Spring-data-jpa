package cn.com.taiji;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.taiji.domain.Student;
import cn.com.taiji.service.StudentService;

public class JpaTest {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * 通过自定义sql语句查询学生
	 * @return
	 */
//	@Ignore
	@Test
	public void findByName1() {
		Student stu = studentService.findByName1("王小明");
		System.out.println(stu.getName()+stu.toString());
	}
	
	/**
	 * 构造查询方法，通过姓名和年龄查询学生
	 * @param student
	 * @return
	 */
	@Ignore
	@Test
	public void findByNameAndAge(){
		Student student = new Student();
		student.setName("王小明");
		student.setAge(24);
        Student stu = studentService
        		.findByNameAndAge(student);
		System.out.println(stu.getName()+stu.getAge());
	}
	/**
	 * 构造的查询方法，通过姓名、年龄和id查询学生
	 * @param student
	 * @return
	 */
	@Ignore
	@Test
	public void findByNameAndIdAndAge(){
		Student student = new Student();
		student.setId(2);
		student.setName("王小红");
		student.setAge(21);
        Student stu = studentService
        		.findByNameAndIdAndAge(student);
        System.out.println(stu.toString());
	}
	
	/**
	 * 添加学生信息
	 * @param student
	 * @return
	 */
	@Ignore
	@Test
	public void insert() {
		Student student = new Student();
		student.setName("王大明");
		student.setAge(25);
		 boolean result = studentService.Insert(student);
		if (result != false) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
	}
	
	@Test
  public void update() {
		Student stu = new Student();
		stu.setId(4);
		stu.setName("王红");
      Student stu1 =  studentService.update(stu);
      System.out.println("student:"+stu1.toString());
  }
	
	
	/**
	 * 通过id删除学生信息
	 * @param student
	 * @return
	 */
	@Ignore
	@Test
	public void delete(Student student) {
		boolean result = studentService.delete(student);
		if (result != false) {
			System.out.println("success");
		}else {
			System.out.println("fail");
		}
	}
	
	@Test
	public void testPages() {
		String map = "{}";
		Map searchParameters = new HashMap();
		try {
			searchParameters = objectMapper.readValue(map, new TypeReference<Map>() {
			});
		} catch (JsonParseException e) {
			//log.error("JsonParseException{}:", e.getMessage());
		} catch (JsonMappingException e) {
			//log.error("JsonMappingException{}:", e.getMessage());
		} catch (IOException e) {
			//log.error("IOException{}:", e.getMessage());
		}
		
		Map mapCar = studentService.getPage(searchParameters);
		System.out.println(mapCar.get("total"));

		System.out.println(mapCar.get("cars"));
	}
	

}
