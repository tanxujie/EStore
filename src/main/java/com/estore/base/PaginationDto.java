package com.estore.base;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class PaginationDto<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7720241562559882834L;

	private int totalPages;

	private int currentPage;

	private List<T> records;
}