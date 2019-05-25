package me.brioschi.acompanytest.io;

import me.brioschi.acompanytest.command.CommandResponseDTO;
import me.brioschi.acompanytest.command.CommandResultMessage;
import me.brioschi.acompanytest.gameengine.CurrentPlayerStatus;
import me.brioschi.acompanytest.world.WorldItem;
import me.brioschi.acompanytest.world.WorldViewDTO;

import java.io.InputStream;
import java.io.PrintStream;
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

    public void prompt() {
        outputStream.print(" > ");
    }

    public void printCommandResult(CurrentPlayerStatus currentPlayerStatus, CommandResponseDTO commandResponse) {

        if (commandResponse.getVisibleWorld() != null) {
            WorldViewDTO visibleWorld = commandResponse.getVisibleWorld();
            printVisibleWorld(currentPlayerStatus, visibleWorld);
        }

        if (commandResponse.getCommandResultMessage() != null) {
            printResultMessage(commandResponse.getCommandResultMessage());
        }

    }

    public void printInvalidCommand(String cmdLine) {
        outputStream.println("[ERROR] > " + cmdLine);
    }

    private void printVisibleWorld(CurrentPlayerStatus currentPlayerStatus, WorldViewDTO visibleWorld) {

        String currentItemDescription = null;

        for (int y = visibleWorld.getYDim(); y >= 0; --y) {
            outputStream.print("          ");
            for (int x = 0; x <= visibleWorld.getXDim(); ++x) {
                if (visibleWorld.getWorldItem(x, y) != null) {
                    WorldItem currentItem = visibleWorld.getWorldItem(x, y);
                    if (currentItem.getPosition().equals(currentPlayerStatus.getCurrentPosition())) {
                        currentItemDescription = currentItem.getDescription();
                        outputStream.print("P");
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

        if ( (currentItemDescription != null) && (!"".equals(currentItemDescription)) ) {
            outputStream.println(currentItemDescription);
        }

    }

    private void printResultMessage(CommandResultMessage commandResultMessage) {
        outputStream.println("[MESSAGE] > " + commandResultMessage);
    }

}
