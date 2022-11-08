package com.freelancingpeter.data;


import java.util.LinkedList;
import java.util.List;


public enum Data{
	  INSTANCE();
	  private LinkedList<String> toots;
	  private LinkedList<String> steam;
	  private int recentGamesCount = 0 ;
	

	  public void setMasto(List<String> tootss) {
		 if(tootss != null)
		 toots.addAll(tootss);
	  }
	  public void setSteam(String steamm) {		
		  if(steamm != null)
			steam.add(steamm);
		  }
	  
	  public List<String> getToots(){
		  return toots;
	  }
	  public void setRecentGames(int noGames) {
		  recentGamesCount = noGames;
	  }
	  public int getRecentGames() {
		  return recentGamesCount;
	  }
	  
	  public List<String> getSteam(){
		  return steam;
	  }
	  public void clearLists() {
		  steam.clear();
		  toots.clear();
	  }
	  
	  public void clearSteam() {
		  steam.clear();
	  }
	  
	  public void clearMasto() {
		  toots.clear();
	  }
	  
	


	// Constructor
	  Data(){
	   toots =  new LinkedList<String>();
	   steam = new LinkedList<String>();
	  }
	}