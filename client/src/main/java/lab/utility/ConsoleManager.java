package lab.utility;

import commands.Command;
import commands.CommandResult;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import exeptions.UnAuthenticated;
import lab.start.AbstractClient;
import lab.start.ReceiveManager;
import lab.start.SendManager;
import utility.CommandPool;
import utility.IOManager;
import utility.Message;
import utility.SpaceMarineArgumentLoader;
import java.io.IOException;

/**
 * Class for separating input and pick approach command
 */

public class ConsoleManager {
    CommandPool commandPool;
    IOManager ioManager;
    Message message = new Message();
    SendManager sendManager;
    ReceiveManager receiveManager;


    public ConsoleManager(CommandPool pool, IOManager ioManager, SendManager sendManager, ReceiveManager receiveManager) {
        commandPool = pool;
        this.ioManager = ioManager;
        this.sendManager = sendManager;
        this.receiveManager = receiveManager;
    }

    public void action(String input) throws EmptyElement, IncorrectData, InterruptedException {
        try {
            String[] splitingInput = input.split("\\s");
            if (input.trim().isEmpty() || splitingInput.length > 2) throw new IllegalArgumentException();
            Command curCommand = commandPool.get(splitingInput[0]);
            checkCommand(curCommand);
            String[] arguments = new String[Math.max(0, splitingInput.length) - 1];
            System.arraycopy(splitingInput, 1, arguments, 0, arguments.length);
            SpaceMarineArgumentLoader argumentLoader = new SpaceMarineArgumentLoader(arguments, ioManager);
            message.loadPreMessage(curCommand, argumentLoader);
            sendManager.sendMessage(message);
            CommandResult result = receiveManager.receiveMessage();
            if (result.getCommand().equals("error"))
                ioManager.printerr(result.getResult());
            else
            ioManager.println(result.getResult());

        } catch (IllegalArgumentException e) {
            ioManager.printerr("Команда не найдена, воспользуйтесь командой \"help\" ");
        } catch (IOException e) {
            ioManager.printerr("Проблемы с отправкой на сервер");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //never throw
        } catch (UnAuthenticated e){
            ioManager.printerr("Сначала войдите или пройдите регистрацию! \n Используйте команды \"sign_in\" или \"log_in\"" );
        }

    }
    private void checkCommand(Command command) throws IncorrectData, UnAuthenticated {
        if (command.getName().equals("exit")){
            ioManager.println("_____Работа программы завершена_____");
            AbstractClient.onStop();
            System.exit(0);
        }
        else if(!(command.getName().equals("log_in")) && !(command.getName().equals("sign_in"))){
            throw new UnAuthenticated();
        }
    }


}


