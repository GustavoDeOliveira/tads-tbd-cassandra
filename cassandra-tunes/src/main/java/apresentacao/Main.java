/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package apresentacao;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import java.util.List;
import negocio.Faixa;

/**
 *
 * @author iapereira
 */
public class Main {

    public static void main(String[] args) {

        Cluster cluster = null;
        try {
            cluster = Cluster.builder().addContactPoint("localhost").build();
            Session session = cluster.connect("tunes");
            Mapper<Faixa> faixaMapper = new MappingManager(session).mapper(Faixa.class);

            Faixa faixa1 = new Faixa("Gila Monster", "King Gizzard & The Lizard Wizard", LocalDate.fromYearMonthDay(2023, 06, 16));

            faixaMapper.save(faixa1);
            
            ResultSet results = session.execute ("select * from faixa");
            Result<Faixa> faixas = faixaMapper.map(results);

            faixas.forEach(x -> System.out.println(x));
            Faixa faixaPersistida = faixas.one();
            
            PreparedStatement prepared = session.prepare("UPDATE faixa SET titulo = ? WHERE id = ?");
            BoundStatement b = prepared.bind("Dragon", faixaPersistida.getId());
            session.execute(b);

            faixaMapper.delete(faixaPersistida.getId());
        } finally {
            if (cluster != null) {
                cluster.close();
            }
        }
    }
}
