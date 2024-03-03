package ua.kiev.prog.case2;

import ua.kiev.prog.shared.Client;
import ua.kiev.prog.shared.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection())
        {
            // remove this
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Clients");
                   //st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            ClientDAOImpl2 dao = new ClientDAOImpl2(conn, "Clients");
            dao.createTable(Client.class);

            Client c = new Client("test", 10);
            Client c1 = new Client("test1" , 23);
            Client c2 = new Client("test" , 30);
            dao.add(c);
            dao.add(c1);
            dao.add(c2);
            int id = c.getId();
            System.out.println(id);
            int id1 = c1.getId();
            System.out.println(id1);
            int id2 = c2.getId();
            System.out.println(id2);

            List<Client> list = dao.getAll(Client.class, "'test'", 10);
            for (Client cli : list)
                System.out.println(cli);

            list.get(0).setAge(30);
            dao.update(list.get(2));

            /* Dz2

            List<Client> list = dao.getAll(Client.class, "name", "age");
            List<Client> list = dao.getAll(Client.class, "age");
            for (Client cli : list)
                System.out.println(cli);

             */

            dao.delete(list.get(0));
        }
    }
}
