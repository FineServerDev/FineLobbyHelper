package eu.ifine.fine.commands;

import eu.ifine.fine.common.FloodgateHandler;
import eu.ifine.fine.common.MotdHandler;
import eu.ifine.fine.common.TransferPacketBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TransferCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "permission.command.error");
            return true;
        }
        Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("transfer")) {
                // /transfer <ip> <port>
                if (args.length == 2) {
                    String ip = args[0];
                    int port = Integer.parseInt(args[1]);
                        Thread thread = new Thread(() -> {
                            boolean isOnline;
                            try {
                                isOnline = MotdHandler.IsOnline(ip, port);
                            } catch (IOException e) {
                                player.sendMessage("§l§6[&gFineLobby§6] §c内部出错，请联系管理员!");
                                return;
                            }
                            if (!isOnline){
                                player.sendMessage("§l§6[§gFineLobby§6] §c目标服务器为离线状态!");
                                return;
                            }
                            if (FloodgateHandler.isFloodgatePlayer(player.getUniqueId())) {
                                TransferPacketBuilder transferPacketBuilder = new TransferPacketBuilder();
                                transferPacketBuilder.sendPacket(ip, port, player.getUniqueId());
                            } else {
                                player.sendMessage("§l§6[§gFineLobby§6] §c目标只能为基岩版玩家");
                            }
                        });
                        thread.start();
                }
        }
        return false;
    }
}
