package com.sinoway.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 临时的8位Id生成器
 * @author xiehao
 *
 */
public final class TempIdGenerator {

	private static int ID_LEN = 8;
	private static TempIdGenerator generator = new TempIdGenerator();

	/**
	 * private and only constructor
	 */
	private TempIdGenerator() {
	}

	/**
	 * @return Generator
	 */
	public static TempIdGenerator getInstance() {
		if (generator == null)
			generator = new TempIdGenerator();
		return generator;
	}

	/**
	 * @return the iD
	 * 
	 */
	public int getID() {
		int root = (int) Math.pow(10, ID_LEN);
		int id = 0;
		do {
			long tmp = Math.abs(Double.doubleToLongBits(Math.random()));
			id = (int) (tmp % root);
		} while (id < (root / 10));
		// System.out.println(id);
		return id;
	}

	/**
	 * @return the iD_LEN
	 */
	public static int getID_LEN() {
		return ID_LEN;
	}

	/**
	 * @param iD_LEN
	 *            the iD_LEN to set
	 */
	public static void setID_LEN(int iD_LEN) {
		ID_LEN = iD_LEN;
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		int x;
		for (int i = 0; i < 10; i++) {
			 System.out.println(generator.getID());
			x = generator.getID();
			if (!list.contains(x))
				list.add(x);
			else
				System.out.println(x);
		}
	}
}
