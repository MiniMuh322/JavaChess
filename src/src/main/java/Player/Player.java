package Player;

import GUI.PlayerName;
import GUI.PlayerColor;
public class Player {
    final PlayerColor color;
    final String player_name;
    public Player(GUI.PlayerColor color, String player_name){
        this.color = color;
        this.player_name = player_name;
    }


    public String getPlayer_name(){return this.player_name;}
    public PlayerColor getPlayer_Color(){return  color;}
}