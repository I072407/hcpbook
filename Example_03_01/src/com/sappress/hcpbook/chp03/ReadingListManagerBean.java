package com.sappress.hcpbook.chp03;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class ReadingListManagerBean
 */
@Stateful
@LocalBean
public class ReadingListManagerBean implements ReadingListManagerBeanLocal {

    /**
     * Default constructor. 
     */
	
	private List<String> readingList = new ArrayList<String>();
	
    public ReadingListManagerBean() {
        // TODO Auto-generated constructor stub
    	
    	
    }

	@Override
	public void addTitle(String title) {
		// TODO Auto-generated method stub
		readingList.add(title);
	}

	@Override
	public boolean removeTitle(String title) {
		// TODO Auto-generated method stub
		return readingList.remove(title);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return readingList.size();
	}

	@Override
	public List<String> getReadingList() {
		// TODO Auto-generated method stub
		return readingList;
	}

}
