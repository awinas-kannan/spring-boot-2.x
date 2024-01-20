package service.producer;

import com.microsoft.azure.eventhubs.EventHubException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class SendToEventHubMain {

    public static void main(String[] args) throws IOException, EventHubException {

        String fileName = "temp.json";
        List<String> filenames = Arrays.asList("/Users/a0m0rtj/AwinasKannan/Official/Workstation/ItemMDM/Azure-EventHub/src/main/resources/servicelayer_2023_01_17_04_55_44_997-0600_4aef4baa-7dde-444a-a433-574fd96d3e2d_0.json");
        Producer eventHubSender = new Producer();

        //Publish from resource folder
//        for(String fn: filenames){
//            eventHubSender.publishEventsFromSysFolder(fn);
//        }



//        for(int i =1;i<101;i++) {`
//            eventHubSender.publishEventsFromConsole("{Test Event " + String.valueOf(i) + "}");
//        }

//        eventHubSender.publishOLD(fileName);
//        eventHubSender.publishEvents(fileName);



        //Publish event from console
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        boolean isRun = true;
        while(isRun){

        String input = reader.readLine();
        if (input.equals("exit"))
            isRun = false;
        eventHubSender.publishEventsFromConsole(input);
        }
    }

}
