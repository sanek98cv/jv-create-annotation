package core.basesyntax.controller;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.lib.Inject;
import core.basesyntax.model.Bet;
import core.basesyntax.model.User;
import java.util.Scanner;

public class ConsoleHandler {
    @Inject
    private BetDao betDao;

    @Inject
    private UserDao userDao;

    public void handle() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("q")) {
                return;
            }
            User user = null;
            Bet bet = null;
            try {
                String[] data = command.split(" ");
                String name = data[0];
                int age = Integer.parseInt(data[1]);
                user = new User(name, age);
                int value = Integer.parseInt(data[2]);
                double risk = Double.parseDouble(data[3]);
                bet = new Bet(value, risk);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Enter correct data!");
            }
            if (user != null && bet != null) {
                userDao.add(user);
                betDao.add(bet);
            }
            System.out.println(user + " " + bet);
        }
    }
}
