import commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import javax.security.auth.login.LoginException;

public class DiscordBot{

    public static void main(String[] args) throws LoginException, InterruptedException {

        JDA jda = JDABuilder.createDefault("OTQ2MTg0OTM3NTc1NDI4MTE2.YhbBZA.loDAO9kRSslVFkhwQuXdu4fdBx4")
                .setActivity(Activity.listening("to your grandma"))
                .addEventListeners(new BotCommands())
                .build().awaitReady(); //added awaitReady so we can get the Guild not until after the bot is done doing its thing

        //Global Command - Can take up to an hour to register and be available
        jda.upsertCommand("fart", "fart with power").queue();

        //Guild Command - Added immediately. Make sure you use awaitReady like above.
        Guild testServer = jda.getGuildById("946147235375251498");
        if(testServer != null){

            //Add the guild commands

            testServer.upsertCommand("food", "Announce your favorite food")
                    .addOption(OptionType.STRING, "food", "the name of the food you like", true)
                    .queue();

            testServer.upsertCommand("add", "Add two numbers")
                    .addOptions(
                            new OptionData(OptionType.INTEGER, "operator1", "the first number", true)
                                    .setRequiredRange(1, Integer.MAX_VALUE),
                            new OptionData(OptionType.INTEGER, "operator2", "the second number", true)
                                    .setRequiredRange(1, Integer.MAX_VALUE)
                    )
                    .queue();

        }

        //Another way of adding slash commands:
//        CommandListUpdateAction commands = jda.updateCommands();
//        commands.addCommands(Commands.slash("fart", "fart with power"));
//        commands.addCommands(Commands.slash("food", "Announce your favorite food")
//                .addOption(OptionType.STRING, "food", "the name of the food you like", true));
//        commands.addCommands(Commands.slash("add", "Add two numbers")
//                .addOptions(
//                        new OptionData(OptionType.INTEGER, "operator1", "the first number", true)
//                                .setRequiredRange(1, Integer.MAX_VALUE),
//                        new OptionData(OptionType.INTEGER, "operator2", "the second number", true)
//                                .setRequiredRange(1, Integer.MAX_VALUE)
//                ));
//        commands.queue();


    }


}
