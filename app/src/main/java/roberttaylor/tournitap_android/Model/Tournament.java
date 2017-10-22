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

    public Tournament() {
    }
    public Tournament(int id, String name, String gameType, String numParticipants, String formatName) {
        this.id = id;
        this.name = name;
        this.gameType = gameType;
        this.numParticipants = numParticipants;
        this.formatName = formatName;
    }

    public Tournament(String name, String gameType, String numParticipants, String formatName) {
        this.name = name;
        this.gameType = gameType;
        this.numParticipants = numParticipants;
        this.formatName = formatName;
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
}
