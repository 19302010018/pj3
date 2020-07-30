package com.show.util.pager;

/**
 * 用于拼接SQL语句
 *
 */
public class Expression {
	private String name;
	private String operator;
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Expression() {
	}

	public Expression(String name, String operator, Object value) {
		super();
		this.name = name;
		this.operator = operator;
		this.value = value;
	}

}
