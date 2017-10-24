package roberttaylor.tournitap_android.Model;

/**
 * Created by Robert Taylor on 10/22/2017.
 */

public class Tournament {
    private int id;
    private String name;
    private String gameType;
    private String numParticipants;
    private String formatName;
    private String stageType;
    private String skillLevel;
    private String description;
    private String totalRounds;

    public Tournament() {
    }
    public Tournament(int id, String name, String gameType, String numParticipants, String formatName,String stageType,
    String skillLevel, String description, String totalRounds) {
        this.id = id;
        this.name = name;
        this.gameType = gameType;
        this.numParticipants = numParticipants;
        this.formatName = formatName;
        this.stageType = stageType;
        this.skillLevel = skillLevel;
        this.description = description;
        this.totalRounds = totalRounds;
    }

    public Tournament(String name, String gameType, String numParticipants, String formatName, String stageType,
                      String skillLevel, String description, String totalRounds) {
        this.name = name;
        this.gameType = gameType;
        this.numParticipants = numParticipants;
        this.formatName = formatName;
        this.stageType = stageType;
        this.skillLevel = skillLevel;
        this.description = description;
        this.totalRounds = totalRounds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(String numParticipants) {
        this.numParticipants = numParticipants;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getStageType(){return stageType;}

    public void setStageType(String stageType){ this.stageType = stageType;}

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds(String totalRounds) {
        this.totalRounds = totalRounds;
    }

}
