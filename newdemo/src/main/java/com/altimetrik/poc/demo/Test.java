package com.altimetrik.poc.demo;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
	
		List<Student> list1=new ArrayList<Student>();
		list1.add(new Student("1", "anil"));
		list1.add(new Student("2", "theja"));
		list1.add(new Student("3", "sunil"));
		
		List<Student> list2=new ArrayList<Student>();
		list2.add(new Student("1", "anil"));
		list2.add(new Student("2", "theja"));
		list1.removeAll(list2);
		System.out.println(list1);
	}

}
