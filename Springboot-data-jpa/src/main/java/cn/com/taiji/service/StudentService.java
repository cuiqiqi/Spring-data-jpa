package cn.com.taiji.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.domain.Student;
import cn.com.taiji.domain.StudentRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	/**
	 * 通过自定义sql语句（通过姓名）查询学生
	 * @return
	 */
	public Student findByName1(String name) {
		Student stu = studentRepository.findByname1(name);
		return stu;
	}
	
	/**
	 * 构造查询方法，通过姓名和年龄查询学生
	 * @param student
	 * @return
	 */
	public Student findByNameAndAge(Student student){
        Student stu = studentRepository
        		.findByNameAndAge(student.getName(),student.getAge());
		return stu;
	}
	/**
	 * 构造查询方法，通过姓名、年龄和id查询学生
	 * @param student
	 * @return
	 */
	public Student findByNameAndIdAndAge(Student student){
        Student stu = studentRepository
        		.findByNameAndIdAndAge(student.getName(),student.getAge(),student.getId());
		return stu;
	}
	/**
	 * 添加学生信息
	 * @param student
	 * @return
	 */
	public boolean Insert(Student student) {
		Student stu = studentRepository.save(student);
		if (stu != null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 通过id删除学生信息
	 * @param student
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean delete(Student student) {
		boolean result = studentRepository.deleteById(student.getId());
		return result;
	}
	/**
	 * 通过id更新学生信息
	 * @param student
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Student update(Student student) {
		Student stu = studentRepository.findOne(student.getId());
		if (student.getName() != null) {
			stu.setName(student.getName());
		}
		if (student.getAge() != null) {
			stu.setAge(student.getAge());
		}
		Student stud = studentRepository.saveAndFlush(stu);
		return stud;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map getPage(final Map searchParameters) {
		Map map = new HashMap();
		int page = 0;
		int pageSize = 10;
		Page<Student> pageList;
		PageRequest pageable;
		List<Order> orders = new ArrayList<Order>();

		if (orders.size() > 0) {
			pageable = new PageRequest(page, pageSize, new Sort(orders));
		} else {
			Sort sort = new Sort(Direction.ASC, "id");
			pageable = new PageRequest(page, pageSize, sort);
		}

		Specification<Student> spec = new Specification<Student>() {
		public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			List<Predicate> list = new ArrayList<Predicate>();
			// 查询出未删除的
			list.add(cb.equal(root.<Integer>get("name"), 1));
			return cb.and(list.toArray(new Predicate[0]));
		}
	};
		pageList = studentRepository.findAll(spec, pageable);
		map.put("total", pageList.getTotalElements());
		List<Student> list = pageList.getContent();
		map.put("student", list);
		return map;
	}

}
