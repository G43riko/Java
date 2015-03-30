package game.vilage.quests;

import game.vilage.buldings.Buildings;
import game.vilage.resources.Suroviny;

import java.util.HashMap;
import java.util.List;

public class Quest {
	private String title;
	private String description;
	private byte questType;
	private byte resourceType;
	private byte from;
	private int value;
	private int id;
	private HashMap<Byte, Boolean> subQestsProgress = new HashMap<Byte, Boolean>();
	
	//CONSTRUCTORS
	
	public Quest(byte questType, byte from, byte resourceType, int value){
		this.questType = questType;
		this.resourceType = resourceType;
		this.from = from;
		this.value = value;
		this.title = Quests.getName(questType);
		id = (int)(Math.random()*10000);
		this.description = "odoslaù " + value + " ks " + Suroviny.getName(resourceType) + " do: " + Buildings.getName(from);
		List<Byte> subQuests = SubQuests.getSubquestsFromQuest(questType);
		for(byte subQuest : subQuests){
			subQestsProgress.put(subQuest, false);
		}
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

	public HashMap<Byte, Boolean> getSubQestsProgress() {
		return subQestsProgress;
	}

	public int getId() {
		return id;
	}
}
