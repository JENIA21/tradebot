package org.example;
import org.decimal4j.util.DoubleRounder;
import org.example.dialog.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static HashMap<String, IDialog> commands = new HashMap<>();

    static {
        commands.put("/start", new DialogStart());
        commands.put("/help", new DialogHelp());
        commands.put("/rate", new DialogRate());
        commands.put("/getAllUsers", new DialogGetUsers());
    }

    public static void getCommand(Scanner scan, User user, DbControl dbControl, String id, Double userValue, String rateValue, String command, String command2){
        System.out.println("У вас сейчас: " + userValue);
        System.out.println("Курс обмена: " + rateValue);
        System.out.println("Сколько?");
        var value = scan.nextLine();
        var valueToNum = value.replaceAll(",", ".");
        var rateValueToNum = rateValue.replaceAll(",", ".");
        var buyValue = DoubleRounder.round(Double.parseDouble(valueToNum) * Double.parseDouble(rateValueToNum), 2);
        if (buyValue > userValue) {
            System.out.println("У вас недостаточно средств. Введите /сhangeCurrency для повторной попытки.");
        } else {
            var sellValue = DoubleRounder.round(userValue - buyValue, 2);

            if(Objects.equals(command, "/ru") && Objects.equals(command2, "/usd")){
                user.setRubles(sellValue);
                user.setDollars(Double.parseDouble(value) + user.getDollars());

            }
            if(Objects.equals(command, "/ru") && Objects.equals(command2, "/eu")){
                user.setRubles(sellValue);
                user.setEuros(Double.parseDouble(value) + user.getEuros());
            }
            dbControl.editUser(user, Integer.parseInt(id));
            System.out.println("Сейчас у вас: " + user);
            System.out.println("Введите /сhangeCurrency для повторной покупки/продажи.");
        }
    }

    public static void main(String[] args) throws SQLException {
        try {
            System.out.println("Введите /start для начала работы с ботом");
            while (true) {
                DbControl dbControl = DbControl.getInstance();
                Scanner scan = new Scanner(System.in);
                String command = scan.nextLine();
                if(Objects.equals(command, "/сhangeCurrency")){
                    RateValue rate = new RateValue();
                    System.out.println("Введите свой id");
                    var id = scan.nextLine();
                    var user = dbControl.getUser(Integer.parseInt(id));
                    System.out.println(user);
                    System.out.println("Введите какую валюту хотите обменять: /ru");
                    var changeCurr = scan.nextLine();

                    var command1 = "/ru";
                    if(Objects.equals(changeCurr, command1)) {
                        System.out.println("На какую валюту хотите поменять: /usd, /eu");
                        var userValue = user.getRubles();
                        var changeToAnotherCurr = scan.nextLine();
                        var commandru1 ="/usd";
                        if (Objects.equals(changeToAnotherCurr, commandru1)) {
                            var rateValue = rate.GetDollarSellRate();
                            getCommand(scan, user, dbControl, id, userValue, rateValue, command1, commandru1);
                        }
                        var commandru2 = "/eu";
                        if (Objects.equals(changeToAnotherCurr, commandru2)) {
                            var rateValue = rate.GetEuroSellRate();
                            getCommand(scan, user, dbControl, id, userValue, rateValue, command1, commandru2);
                        }
                    }
                }

                if(Objects.equals(command, "/addCurrency")) {
                    System.out.println("Введите свой id");
                    var id = scan.nextLine();
                    var user = dbControl.getUser(Integer.parseInt(id));
                    System.out.println("Сколько рублей хотите положить на баланс?");
                    var amoumt = scan.nextLine();
                    var amountToNum = DoubleRounder.round(Double.parseDouble(amoumt.replaceAll(",", ".")), 2);
                    user.setRubles(amountToNum + user.getRubles());
                    dbControl.editUser(user, Integer.parseInt(id));
                    System.out.println(user);
                }
                if (commands.get(command) != null){
                    IDialog iDialog = commands.get(command);
                    System.out.println(iDialog.execute());
                }
                else{
                    System.out.println("Введите /help");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

