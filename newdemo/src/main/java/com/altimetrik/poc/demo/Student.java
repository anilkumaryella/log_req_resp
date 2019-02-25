package com.altimetrik.poc.demo;



public class Student {

	private String id;
	private String name;
	
	
	 public String getId() {
		return id;
	}


	public Student(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	    public boolean equals(Object obj) {

	        try {
	        	Student licenceDetail  = (Student) obj;
	            return name.equals(licenceDetail.getName());
	        }
	        catch (Exception e)
	        {
	            return false;
	        }

	    }
}
