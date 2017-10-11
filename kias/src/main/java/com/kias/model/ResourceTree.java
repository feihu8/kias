package com.kias.model;

import java.util.List;

public class ResourceTree extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    private List<ResourceTree> children;
    

	public List<ResourceTree> getChildren() {
		return children;
	}

	public void setChildren(List<ResourceTree> children) {
		this.children = children;
	}


	private boolean checked;


	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
