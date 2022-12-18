package eu.ifine.fine.papi;


import com.alibaba.fastjson2.JSONObject;
import eu.ifine.fine.common.MotdHandler;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

import java.util.Objects;

public class ServerMotd extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "FineDev";
    }

    @Override
    public String getIdentifier() {
        return "fine";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("name")) {
            return player == null ? null : player.getName(); // "name" requires the player to be valid
        }
        if(params.toLowerCase().startsWith("server_version_")) {
            //获取占位符IP与端口
            String ip_port = params.split("(?i)server_version_")[1];
            String ip = ip_port.split(":")[0];
            int port = Integer.parseInt(ip_port.split(":")[1]);

            String be=  MotdHandler.MotdPe(ip, port);
            JSONObject jsonObject = JSONObject.parseObject(be);
            if (!Objects.equals(jsonObject.getString("status"), "fail")){
                return jsonObject.getString("version");
            }else{
                return "null";
            }
        }else if(params.toLowerCase().startsWith("server_status_")) {
            String ip_port = params.split("(?i)server_status_")[1];
            String ip = ip_port.split(":")[0];
            int port = Integer.parseInt(ip_port.split(":")[1]);

            String be=  MotdHandler.MotdPe(ip, port);
            JSONObject jsonObject = JSONObject.parseObject(be);
            if (!Objects.equals(jsonObject.getString("status"), "fail")){
                return "在线";
            }else{
                return "离线";
            }
        }else if(params.toLowerCase().startsWith("server_players_")) {
            String ip_port = params.split("(?i)server_players_")[1];
            String ip = ip_port.split(":")[0];
            int port = Integer.parseInt(ip_port.split(":")[1]);

            String be=  MotdHandler.MotdPe(ip, port);
            JSONObject jsonObject = JSONObject.parseObject(be);
            if (!Objects.equals(jsonObject.getString("status"), "fail")){
                return jsonObject.getInteger("online").toString();
            }else{
                return "null";
            }
        }

        return null; // Placeholder is unknown by the Expansion
    }
}