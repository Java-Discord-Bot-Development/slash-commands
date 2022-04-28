package commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

public class BotCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        if(event.getName().equals("fart")){

            //Send a message in response to the command being run
            event.reply("You just farted").queue();

        }else if(event.getName().equals("food")){

            //Notify discord that the command was received and we are processing it.
            // if you don't defer, you need to hurry up and reply or it will not work.
            event.deferReply().queue();

            //Grab the name of your favorite food from the slash command arguments or "options"
            OptionMapping foodOption = event.getOption("food");
            if(foodOption == null){

                event.getHook().sendMessage("Food not provided for some reason").queue();

                return;
            }

            String favoriteFood = foodOption.getAsString();

            //Since we deferred the reply, if we want to reply to it
            // we have to use the "hook".
            event.getHook().sendMessage("My favorite food is: " + favoriteFood).queue();

        }else if(event.getName().equals("add")){

            OptionMapping operator1 = event.getOption("operator1");
            OptionMapping operator2 = event.getOption("operator2");

            if(operator1 == null || operator2 == null){
                return;
            }

            int sum = operator1.getAsInt() + operator2.getAsInt();

            event.reply("The sum is: " + sum).queue();

        }

    }
}
