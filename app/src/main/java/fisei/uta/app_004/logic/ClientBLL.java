package fisei.uta.app_004.logic;

import android.content.ContentValues;
import android.content.Context;

import fisei.uta.app_004.entities.Client;
import fisei.uta.app_004.dal.ClientDAL;
public class ClientBLL {
    Context context;
    ClientDAL clientDAL;
    public ClientBLL(Context context) {
        this.context = context;
        clientDAL = new ClientDAL(context);
    }

    public long insert(Client client) {

        long count = 0;

        try {
            //ClientDAL clientDAL = new ClientDAL(context);

            // validaciones simples o complejas.
            // reglas del negocio.
            if ( (client.getBalance() > 0)  || (client.getBalance() <= 100000) ) {
                count = clientDAL.insert(client);
            }
            else {
                throw new Exception("El balance debe estar entre 1 y 10.000");
                //new Exception();
            }

        }
        catch (Exception e) {
            //throw e;
        }
        finally {

        }

        return count;
    }
}
