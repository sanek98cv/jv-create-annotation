package core.basesyntax.factory;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.BetDaoImpl;

public class FactoryBet {
    private static BetDao betDao;

    public static BetDao getBetDao() {
        if (betDao == null) {
            betDao = new BetDaoImpl();
        }
        return betDao;
    }
}
