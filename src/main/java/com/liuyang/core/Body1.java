package com.liuyang.core;

import java.io.Serializable;

import com.liuyang.model.megacorp.Employee;

public class Body1 implements Serializable {

	private static final long serialVersionUID = 4374358092763006333L;

	private String _index;
	private String _type;
	private String _id;
	private int _version;
	private boolean found;
	private Employee _source;

	public String get_index() {
		return _index;
	}

	public void set_index(String _index) {
		this._index = _index;
	}

	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int get_version() {
		return _version;
	}

	public void set_version(int _version) {
		this._version = _version;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public Employee get_source() {
		return _source;
	}

	public void set_source(Employee _source) {
		this._source = _source;
	}

	@Override
	public String toString() {
		return "Body1 [_index=" + _index + ", _type=" + _type + ", _id=" + _id + ", _version=" + _version + ", found="
				+ found + ", _source=" + _source + "]";
	}

}
