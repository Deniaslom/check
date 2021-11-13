package generation;

import beans.ClientCard;

import java.util.ArrayList;
import java.util.List;

public class GenerationClientsCardDB {
    private static List<ClientCard> clientCarts = new ArrayList<>();
    private static volatile GenerationClientsCardDB instance;

    private GenerationClientsCardDB() {
    }

    public static GenerationClientsCardDB getInstance(){
        if(instance == null)
            synchronized (GenerationClientsCardDB.class){
                if(instance == null)
                    instance = new GenerationClientsCardDB();
            }
        return instance;
    }


    public static List<ClientCard> getClientCards(){
        for(int i = 1000; i < 2000; i++){
            ClientCard clientCart = new ClientCard();
            clientCart.setId(i);
            clientCarts.add(clientCart);
        }

        return clientCarts;
    }

}
