package central;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class DataBase {

    public String status = "Não conectou...";

    public int add_cliente(Connection con, String nome_utilizador, String password) throws SQLException, ClassNotFoundException {
        int tamanho_nome = nome_utilizador.length();
        int tamanho_password = password.length();
        Connection connection = con;
        //System.out.println("Connection -> " + connection);
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from utilizador");
        while (resultset.next()) {
            if (resultset.getString("nome_utilizador").equals(nome_utilizador)) {
                return 1;
            }
        }
        if (tamanho_password > 10) {
            return 2;
        }
        if (tamanho_nome > 10) {
            return 3;
        }
        statement.executeUpdate("insert into utilizador values(null,'" + nome_utilizador + "','" + password + "',1)");
        return 0;
    }

    public int add_gestor(Connection con, String nome_utilizador, String password) throws SQLException, ClassNotFoundException {
        int tamanho_nome = nome_utilizador.length();
        int tamanho_password = password.length();
        Connection connection = con;
        //System.out.println("Connection -> " + connection);
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from utilizador");
        while (resultset.next()) {
            if (resultset.getString("nome_utilizador").equals(nome_utilizador)) {
                return 1;
            }
        }
        if (tamanho_password > 10) {
            return 2;
        }
        if (tamanho_nome > 10) {
            return 3;
        }
        statement.executeUpdate("insert into utilizador values(null,'" + nome_utilizador + "','" + password + "',2)");
        return 0;
    }

    public void delete_utilizador(Connection con, int cod_utilizador) throws SQLException, ClassNotFoundException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate("delete from utilizador where cod_utilizador = " + cod_utilizador);
    }

    public void ver_tabelaUtilizador(Connection con) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from utilizador");
        while (resultset.next()) {
            System.out.println(resultset.getInt("cod_utilizador") + "\t\t" + resultset.getString("nome_utilizador") + "\t\t" + resultset.getString("password") + "\t\t" + resultset.getInt("cod_tipo"));
        }
    }

    public int ver_existe_user(Connection con, String nome) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from utilizador");
        while (resultset.next()) {
            if (nome.equals(resultset.getString("nome_utilizador"))) {
                /*existe*/
                return resultset.getInt("cod_utilizador");
            }
        }
        return 0;
    }

    public String nome_cod_user(Connection con, int cod_utilizador) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT u.nome_utilizador FROM utilizador u where u.cod_utilizador=" + cod_utilizador);
        while (resultset.next()) {
            return resultset.getString("nome_utilizador");
        }
        return null;
    }

    public HashMap<String, Integer> users_tipo(Connection con) throws SQLException {
        //ArrayList<String> todos=new ArrayList<String>();
        HashMap<String, Integer> todos_tipo = new HashMap<String, Integer>();
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from utilizador");
        while (resultset.next()) {
            //todos.add(resultset.getString("nome_utilizador"));
            todos_tipo.put(resultset.getString("nome_utilizador"), resultset.getInt("cod_tipo"));
        }
        return todos_tipo;
    }

    public ArrayList<String> todos_users(Connection con) throws SQLException {
        ArrayList<String> todos= new ArrayList<String>();
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from utilizador");
        while (resultset.next()) {
            todos.add(resultset.getString("nome_utilizador"));
        }
        return todos;
    }

    public HashMap<String, String> users_pass(Connection con) throws SQLException {
        //ArrayList<String> todos=new ArrayList<String>();
        HashMap<String, String> todos_pass = new HashMap<String, String>();
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from utilizador");
        while (resultset.next()) {
            //todos.add(resultset.getString("nome_utilizador"));
            todos_pass.put(resultset.getString("nome_utilizador"), resultset.getString("password"));
        }
        return todos_pass;
    }

    public int add_consumo(Connection con, int cod_edificio, double t_valor, long timestamp) throws SQLException, ClassNotFoundException {
        Date date = new Date(1, 1, 1);
        Time time = new Time(1, 1, 1);
        //timestamp = 1528496410000L;
        date.setTime(timestamp);
        System.out.println(date);
        time.setTime(timestamp);
        System.out.println(time);

        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT e.cod_edificio FROM edificio e");
        while (resultset.next()) {
            if (resultset.getInt("cod_edificio") == cod_edificio) {/*edificio encontrado*/
                statement.executeUpdate("insert into consumo values(null," + cod_edificio + "," + t_valor + ",'" + date + "','" + time + "')");
                ver_tabelaConsumo(con);
                return 0;
            }
        }
        return 1;
    }

    public void delete_consumo(Connection con, int cod_consumo) throws SQLException, ClassNotFoundException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate("delete from consumo where cod_consumo = " + cod_consumo);
    }

    public void ver_tabelaConsumo(Connection con) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from consumo");
        while (resultset.next()) {
            System.out.println(resultset.getInt("cod_consumo") + "\t\t" + resultset.getInt("cod_edificio") + "\t\t" + resultset.getFloat("valor") + "\t\t" + resultset.getDate("timestamp_date") + "\t\t" + resultset.getTime("timestamp_time"));
        }
    }

    public int add_edifício(Connection con, String nome_utilizador, String designacao, double preco) throws SQLException, ClassNotFoundException {
        //System.out.println("Add_edificio");
        Connection connection = con;
        int cod_utilizador = 0;
        //System.out.println("Connection -> " + connection);
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT u.cod_utilizador FROM utilizador u where u.nome_utilizador='" + nome_utilizador + "'");
        while (resultset.next()) {
            cod_utilizador = resultset.getInt("cod_utilizador");
        }
        resultset = statement.executeQuery("SELECT e.designacao FROM edificio e where e.cod_utilizador=" + cod_utilizador);
        while (resultset.next()) {
            if (resultset.getString("designacao").equals(designacao)) {
                return 1;
            }
        }
        if (cod_utilizador != 0) {
            statement.executeUpdate("insert into edificio values(null," + cod_utilizador + ",'" + designacao + "'," + preco + ")");
            return 2;
        }
        return 0;
    }

    public double ver_existe_edificio(Connection con, int code) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT e.preco FROM edificio e where e.cod_edificio=" + code);
        while (resultset.next()) {
            return resultset.getDouble("preco");
        }
        return 0;
    }

    public ResultSet output_cliente(Connection con, String designacao) throws SQLException, ParseException {
        //SELECT e.designacao, c.timestamp_date, c.timestamp_time, e.preco FROM edificio e, consumo c where e.cod_edificio=c.cod_edificio and e.cod_edificio=3;
        //SELECT c.valor, c.timestamp_date, c.timestamp_time, e.preco FROM edificio e, consumo c where e.cod_edificio=c.cod_edificio and e.designacao="casa Franca";
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT c.valor, c.timestamp_date, c.timestamp_time, e.preco FROM edificio e, consumo c where e.cod_edificio=c.cod_edificio and e.designacao='" + designacao + "'");
        //System.out.println(resultset);
//while (resultset.next()) {
        /*System.out.println(resultset.getDouble("valor"));
            String s = Double.toString(resultset.getDouble("valor"));
            System.out.println(s);
            System.out.println(Double.parseDouble(s));

            System.out.println(resultset.getDate("timestamp_date"));
            s = String.valueOf(resultset.getDate("timestamp_date"));
            System.out.println(s);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            System.out.println((Date) formatter.parse(s));

            System.out.println(resultset.getTime("timestamp_time"));
            s = String.valueOf(resultset.getTime("timestamp_time"));
            System.out.println(s);
            System.out.println(Time.parse(s));

            System.out.println(resultset.getDouble("preco"));
            s = Double.toString(resultset.getDouble("preco"));
            System.out.println(s);
            System.out.println(Double.parseDouble(s));*/
        //}
        return resultset;

    }

    public double output_cliente_preco(Connection con, String designacao) throws SQLException, ParseException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT e.preco FROM edificio e, consumo c where e.cod_edificio=c.cod_edificio and e.designacao='" + designacao + "' limit 1");
        System.out.println(resultset);
        while (resultset.next()) {
            return resultset.getDouble("preco");
        }
        return 0;
    }

    public boolean alterar_preco(Connection con, String nome_utilizador, String designacao, double preco) throws SQLException{
        //update edificio set preco=15 where cod_utilizador=2 and designacao="aluguer Braga";
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
       
        resultset = statement.executeQuery("SELECT u.cod_utilizador FROM utilizador u where u.nome_utilizador='" + nome_utilizador + "'");
        int code=0;
        while (resultset.next()) {
             code= resultset.getInt("cod_utilizador");
             //System.out.println(code);
        }
        
        if(code!=0){
            //System.out.println("entrou 1");
            statement.executeUpdate("update edificio set preco="+preco+" where cod_utilizador="+code+" and designacao='"+designacao+"'");
            //System.out.println("entrou 2");
            return true;
        }
        return false;
        
    }
    public HashMap<String, ArrayList<Integer>> users_edificios(Connection con) throws SQLException {
        HashMap<String, ArrayList<Integer>> todos_edif = new HashMap<String, ArrayList<Integer>>();
        ArrayList<Integer> trocar = new ArrayList<Integer>();
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT u.nome_utilizador, e.cod_edificio FROM utilizador u, edificio e where e.cod_utilizador=u.cod_utilizador");

        while (resultset.next()) {
            //System.out.println("\t------> "+resultset.getString("nome_utilizador")+", "+resultset.getInt("cod_edificio"));
            if (todos_edif.isEmpty()) {
                //System.out.println("AINDA VAZIO");
                trocar.add(resultset.getInt("cod_edificio"));           //adiciono num novo array
                todos_edif.put(resultset.getString("nome_utilizador"), trocar);
            } else {
                auxiliar_users_edificios(resultset, todos_edif);
            }
        }
        resultset = statement.executeQuery("SELECT u.nome_utilizador, e.cod_edificio FROM utilizador u, edificio e where e.cod_utilizador=u.cod_utilizador");

        /*while(resultset.next()){ 
            System.out.println(resultset.getString("nome_utilizador")+", "+resultset.getInt("cod_edificio"));
        }*/
        //System.out.println(todos_edif);
        return todos_edif;
    }

    public HashMap<String, ArrayList<Integer>> auxiliar_users_edificios(ResultSet resultset, HashMap<String, ArrayList<Integer>> todos_edif) throws SQLException {
        ArrayList<Integer> trocar = new ArrayList<Integer>();
        for (String x : todos_edif.keySet()) {
            if (x.equals(resultset.getString("nome_utilizador"))) {       //ja existe no hasmap
                /*System.out.println("Já existe");
                System.out.println("entrou "+ x);*/
                trocar = new ArrayList<Integer>();
                trocar = todos_edif.get(x);
                trocar.add(resultset.getInt("cod_edificio"));
                todos_edif.put(resultset.getString("nome_utilizador"), trocar);
                return todos_edif;
            }
        }
        //System.out.println("Não existe");//nao existe no hashmap  
        trocar = new ArrayList<Integer>();
        //System.out.println("adicionou "+ resultset.getString("nome_utilizador")+ " : "+resultset.getInt("cod_edificio"));
        trocar.add(resultset.getInt("cod_edificio"));
        //System.out.println(trocar);
        todos_edif.put(resultset.getString("nome_utilizador"), trocar);
        return todos_edif;
    }

    public ArrayList<String> pesquisar_edificio_user(Connection con, String user) throws SQLException {
        ArrayList<String> novo = new ArrayList<String>();
        int code = 0;
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT u.cod_utilizador FROM utilizador u where u.nome_utilizador='" + user + "'");
        while (resultset.next()) {
            code = resultset.getInt("cod_utilizador");
        }
        //System.out.println("codigo : "+code);

        resultset = statement.executeQuery("SELECT e.designacao FROM edificio e where cod_utilizador=" + code);

        while (resultset.next()) {
            novo.add(resultset.getString("designacao"));
        }
        //System.out.println(novo);
        return novo;
    }

    public boolean delete_edifício(Connection con, String designacao, String nome_utilizador) throws SQLException, ClassNotFoundException {
        Connection connection = con;
        int cod_utilizador = 0;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT u.cod_utilizador FROM utilizador u where u.nome_utilizador='" + nome_utilizador + "'");
        while (resultset.next()) {
            cod_utilizador = resultset.getInt("cod_utilizador");
        }
        if (cod_utilizador != 0) {
            statement.executeUpdate("delete from edificio where designacao = '" + designacao + "' and cod_utilizador=" + cod_utilizador);
            return true;
        }
        return false;
    }

    public void ver_tabelaEdifício(Connection con) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select *from edificio");
        while (resultset.next()) {
            System.out.println(resultset.getInt("cod_edificio") + "\t\t" + resultset.getInt("cod_utilizador"));
        }
    }

    public String ver_Tipo(Connection con, int cod_tipo) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("select designacao from tipo where cod_tipo=" + cod_tipo);
        return (resultset.getString("designacao"));
    }

    public int ver_Tipo_user(Connection con, String nome_utilizador) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        resultset = statement.executeQuery("SELECT u.cod_tipo FROM utilizador u where u.nome_utilizador='" + nome_utilizador + "'");
        while (resultset.next()) {
            return resultset.getInt("cod_tipo");
        }
        return 0;
    }

    public java.sql.Connection getConexaoMySQL() throws ClassNotFoundException, SQLException, ParseException {
        Connection connection = null;

        try {
            /*Class.forName("org.apache.derby.jdbc.ClientDriver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/faseb", "root", null);*/

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BD", "root", null);

            /*Testar a conexão*/
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");

            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            //alterar_preco(connection, "Claudia", "aluguer Braga", 0.022);
            //add_consumo(connection, 6, 0.00608, 1528496410000L);
            //ver_tabelaConsumo(connection);

            /*
            2018-06-08
            23:20:10
             */
            //String s = "1528496876877";
            /*Date date = new Date(1, 1, 1);
            Time time = new Time(1, 1, 1);*/
 /*long timestamp = Long.parseLong(s);
            System.out.println("s:" + s + "\tconversao : " + timestamp);*/
 /*date.setTime(valor);
            System.out.println(date);
            time.setTime(valor);
            System.out.println(time);*/
 /*String array[] = new String[2];
            Double valor;
            array[1]="0,006";
            array[1]=array[1].replaceAll(",", ".");
            valor=Double.parseDouble(array[1]);
            add_consumo(connection, 6, valor, timestamp);
            ver_tabelaConsumo(connection);
            System.out.println("con : "+connection);*/
 /*System.out.println(existe_edificio(connection,6));
            System.out.println(existe_edificio(connection,1));*/
            //System.out.println(output_cliente_valor(connection, "casa Franca"));
            return connection;

        } catch (SQLException e) {
            System.out.println("ERRO: Nao foi possivel conectar ao Banco de Dados.");
            return null;
            /*}finally{
            if(resultset!=null){
                resultset.close();
            }
            if(connection!=null){
                connection.close();
            }
            if(statement!=null){
                statement.close();
            }   */
        }
    }

    public String statusConection() {
        return status;
    }

    public void Fechar(Connection con) throws SQLException {
        Connection connection = con;
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultset = null;
        if (resultset != null) {
            resultset.close();
        }
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
    }
}
