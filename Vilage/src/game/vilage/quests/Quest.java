package game.vilage.quests;

import game.vilage.buldings.Buildings;
import game.vilage.resources.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Quest {
	private static ArrayList<Long> times = new ArrayList<Long>();
	private String title;
	private String description;
	private byte questType;
	private byte resourceType;
	private byte from;
	private int value;
	private int completedSubQuests = 0;
	private long time;
	private HashMap<Byte, Integer> subQestsProgress = new HashMap<Byte, Integer>();
	
	//CONSTRUCTORS
	
	public Quest(byte questType, byte from, byte resourceType, int value){
		this.questType = questType;
		this.resourceType = resourceType;
		this.from = from;
		this.value = value;
		this.title = Quests.getName(questType);
		time = System.currentTimeMillis();
		while(times.contains(time)){
			time++;
		}
		times.add(time);
		
		this.description = "odoslaù " + value + " ks " + Resources.getName(resourceType) + " do: " + Buildings.getName(from);
		List<Byte> subQuests = SubQuests.getSubquestsFromQuest(questType);
		for(byte subQuest : subQuests){
			subQestsProgress.put(subQuest, 0);
		}
	}

	//OTHERS
	
	public void completeSubQuest(){
		completedSubQuests++;
	}
	
	//OVERRIDES
	
	public String toString(){
		return description;
	}
	
	public boolean equals(Object o){
		Quest q = (Quest)o;
		return q.time == time;
		
	}
	
	//GETTERS
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public byte getQuestType() {
		return questType;
	}

	public byte getResourceType() {
		return resourceType;
	}

	public byte getFrom() {
		return from;
	}

	public int getValue() {
		return value;
	}

	public HashMap<Byte, Integer> getSubQestsProgress() {
		return subQestsProgress;
	}

	public long getTime() {
		return time;
	}

	public int getCompletedSubQuests() {
		return completedSubQuests;
	}
}
