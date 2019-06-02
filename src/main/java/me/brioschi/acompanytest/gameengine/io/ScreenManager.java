package me.brioschi.acompanytest.gameengine.io;

import me.brioschi.acompanytest.gameengine.command.CommandResponseDTO;
import me.brioschi.acompanytest.gameengine.command.CommandResultMessage;
import me.brioschi.acompanytest.domain.character.Player;
import me.brioschi.acompanytest.domain.monster.Monster;
import me.brioschi.acompanytest.domain.monster.MonsterId;
import me.brioschi.acompanytest.persistence.MonsterRepository;
import me.brioschi.acompanytest.domain.world.WorldItem;
import me.brioschi.acompanytest.domain.world.WorldViewDTO;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class ScreenManager {

    private final Scanner inputScanner;
    private final PrintStream outputStream;

    public ScreenManager(InputStream inputStream, PrintStream outputStream) {
        this.inputScanner = new Scanner(inputStream);
        this.outputStream = outputStream;
    }

    public String nextLine() {
        return inputScanner.nextLine();
    }

    public void prompt(Player currentPlayer) {
        if (currentPlayer != null) {
            outputStream.println(" [" + currentPlayer.getCurrentExperience().getExperience() + "] " + currentPlayer.getName() + " > ");
        } else {
            outputStream.println(" > ");
        }
    }

    public void printCommandResult(Player currentPlayer, MonsterRepository monsterRepository, CommandResponseDTO commandResponse) {

        if (commandResponse.getVisibleWorld() != null) {
            WorldViewDTO visibleWorld = commandResponse.getVisibleWorld();
            printVisibleWorld(currentPlayer, monsterRepository, visibleWorld);
        }

        if (commandResponse.getCommandResultMessage() != null) {
            printResultMessage(commandResponse.getCommandResultMessage());
        }

    }

    public void printInvalidCommand(String cmdLine) {
        outputStream.println("[ERROR] > " + cmdLine);
    }

    private void printVisibleWorld(Player currentPlayer, MonsterRepository monsterRepository, WorldViewDTO visibleWorld) {

        String currentItemDescription = null;
        List<MonsterId> currentItemMonsterIdList = null;

        for (int y = visibleWorld.getYDim(); y >= 0; --y) {
            outputStream.print("          ");
            for (int x = 0; x <= visibleWorld.getXDim(); ++x) {
                if (visibleWorld.getWorldItem(x, y) != null) {
                    WorldItem currentItem = visibleWorld.getWorldItem(x, y);
                    if (currentItem.getPosition().equals(currentPlayer.getCurrentPosition())) {
                        currentItemDescription = currentItem.getDescription();
                        currentItemMonsterIdList = currentItem.getMonsterIds();
                        outputStream.print("P");
                    } else if ( currentItem.getMonsterIds().size() > 0) {
                        outputStream.print("@");
                    } else if (currentItem.getWorldItemType() == WorldItem.WorldItemType.STREET) {
                        outputStream.print("=");
                    } else if (currentItem.getWorldItemType() == WorldItem.WorldItemType.FLOOR) {
                        outputStream.print("#");
                    }
                } else {
                    outputStream.print(" ");
                }
            }
            outputStream.println();
        }
        outputStream.println();

        if ( (currentItemDescription != null) && (!"".equals(currentItemDescription)) ) {
            outputStream.println(currentItemDescription);
            outputStream.println();
        }

        if ( currentItemMonsterIdList != null) {
            for (MonsterId monsterId : currentItemMonsterIdList) {
                Monster monster = monsterRepository.load(monsterId);
                outputStream.println( "   @ [" +  monster.getExperience().getExperience() + "] " + monster.getName() + ": " + monster.getDescription());
            }
            outputStream.println();
        }

    }

    private void printResultMessage(CommandResultMessage commandResultMessage) {
        outputStream.println("[MESSAGE] > " + commandResultMessage);
        outputStream.println();
    }

}
