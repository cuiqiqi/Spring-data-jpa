package cn.com.taiji.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;
/**
 * @Data：      注解中包含了get()和set()方法，还有toString()方法
 * @Table：   指定数据库生成的表名
 * @Entity：修饰实体类，指明该类将映射到指定的数据表
 * @GeneratedValue(strategy=GenerationType.IDENTITY)：主键自增长策略
 * @Column：写在属性上方，对应数据库表，不写就是默认
 * @author Cqq
 */
@Data
@Entity
@Table(name="stu_table") 
public class Student {
	@Id  //设置主键
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	private String name;
	private Integer age;
	
	
}
