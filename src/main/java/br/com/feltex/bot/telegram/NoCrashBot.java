package br.com.feltex.bot.telegram;

import com.vdurmont.emoji.EmojiParser;
import java.util.Objects;
import java.util.regex.Pattern;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class NoCrashBot extends TelegramLongPollingBot {

    private Boolean controladorToken = false;
    private static String token;

    @Override
    public String getBotUsername() {
        return "NoCrash_bot";
    }

    @Override
    public String getBotToken() {
        return "5586195019:AAF5ZMyRiINSicMGAEprsVubO9I5hJ0-njo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            DatabaseInformation di = new DatabaseInformation();
            SendMessage sm = new SendMessage();
            SendMessage wel = new SendMessage();
            StringBuilder sb = new StringBuilder();
            String answear = "Mensagem Inv�lida  :injured:";

            if (update.getMessage().getText().equals("/start")) {
                wel.setChatId(update.getMessage().getChatId().toString());
                wel.setText(EmojiParser.parseToUnicode(
                        String.format("Seja bem vindo(a) %s!", update.getMessage().getFrom().getFirstName()
                        )));
                try {
                    execute(wel);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                sendMessages(welcomeMessage(), update.getMessage().getChatId().toString());
                answear = "";
            }

            if (controladorToken) {
                if (Objects.nonNull(token)) {
                    this.controladorToken = false;
                }
                if (Pattern.matches("[A-Z0-9]{7}", update.getMessage().getText())) {
                    this.token = update.getMessage().getText();
                    this.controladorToken = false;
                    sendMessages("Token configurado!\n", update.getMessage().getChatId().toString());
                    answear = "Por favor execute o comando novamente";

                    sendMessages(welcomeMessage(), update.getMessage().getChatId().toString());
                } else {
                    answear = "Token inv�lido";
                }
            }

            if (update.getMessage().getText().equals("/computerinfo") || update.getMessage().getText().equals("/cpudiario")
                    || update.getMessage().getText().equals("/cpusemanal") && Objects.isNull(this.token)) {
                sb.append("Primeiro digite o TOKEN da sua m�quina");
                sb.append("\n\nps: ele � gerado quando voc� cadastra uma desktop em nosso site ");
                sb.append("digite /info para mais informa��es");
                this.controladorToken = true;
                answear = sb.toString();
            }

            if (update.getMessage().getText().equals("/cpudiario") && Objects.nonNull(this.token)) {
                System.out.println(token);
                try {
                    answear = "A m�dia de uso do seu processador hoje foi de " + di.usoProcessadorDiario(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (update.getMessage().getText().equals("/cpusemanal") && Objects.nonNull(this.token)) {
                try {
                    answear = "A m�dia de uso semanal do seu processador foi de " + di.usoProcessadorSemanal(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (update.getMessage().getText().equals("/computerinfo") && Objects.nonNull(this.token)) {
                try {
                    answear = di.desktopInformation(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (update.getMessage().getText().equals("/info")) {
                
                sb.append("NoCrash � um software de gerenciamento de hardware, ");
                sb.append("com ele � poss�vel ver em tempo real a situa��o da ");
                sb.append("sua m�quina, te avisando quando alguma pe�a est� sobrecarregada.  :collision:");
                sb.append("\n\nVoc� ainda pode contar com as nossas recomenda��es ");
                sb.append("totalmente personalizadas para seu tipo de uso!");
                sb.append("\n\nPara ter acesso total aos nossos servi�os, acesse o site: \n");
                sb.append("http://localhost:3333/dashboard.html  :pushpin:");
                answear = sb.toString();
            }

            if (update.getMessage().getText().equals("/logout")) {
                sb.append("Deslogado com sucesso!");
                sb.append("\n\nAgradecemos por usar nossos servi�os!  :blush:");
                this.token = null;
                answear = sb.toString();
            }

            sm.setChatId(update.getMessage().getChatId().toString());
            sm.setText(EmojiParser.parseToUnicode(answear));

            if (answear.equals("Mensagem Inv�lida  :injured")) {
                sendMessages(welcomeMessage(), update.getMessage().getChatId().toString());
            }
            try {
                execute(sm);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    private void sendMessages(String message, String chatId) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText(EmojiParser.parseToUnicode(message));

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String welcomeMessage() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nEu sou o Marcelo :bot_face:\n\nComo posso te ajudar hoje?  :thinking: :thought_balloon:");
        sb.append("\n\n/computerinfo - Veja as informa��es do seu computador em tempo real")
                .append("  :computer:");
        sb.append("\n\n/cpudiario - Ver a m�dia di�ria do uso do seu processador  :envelope_with_arrow:");
        sb.append("\n\n/cpusemanal - Ver a m�dia semanal do uso do seu processador    :email:");
        sb.append("\n\n/info - Como funciona o NoCrash");
        sb.append("\n\n/logout - Deslogar da m�quina");
        return sb.toString();
    }
}
