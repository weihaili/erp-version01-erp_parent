package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tree implements Serializable {

	/**
	 * need network transmit and version controller
	 */
	private static final long serialVersionUID = -7863209085693322173L;
	
	private String id;//show menu id
	private String text;//show menu name
	private String state;//node state，'open' or 'closed'，default：'open'。if 'closed' do not auto open。
	private boolean checked;//represent this node is checked。
	private String attributes;// has been added the node attribute
	private List<Tree> children;// declare more nodes use array under current node
	public void setId(String id) {
		this.id = id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public String getId() {
		return id;
	}
	public String getText() {
		return text;
	}
	public String getState() {
		return state;
	}
	public boolean isChecked() {
		return checked;
	}
	public String getAttributes() {
		return attributes;
	}
	public List<Tree> getChildren() {
		if (null==children) {
			children=new ArrayList<Tree>();
		}
		return children;
	}
 

}
