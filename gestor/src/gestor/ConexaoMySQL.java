package gestor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConexaoMySQL {

    public String status = "Não conectou...";

    /*codigo da area com a sua lista de sensores*/
    HashMap<Integer, ArrayList<Integer>> area_sensores = new HashMap<Integer, ArrayList<Integer>>();
    ArrayList<Integer> cod_sensor = new ArrayList();

    /*codigo da area e sua designacao*/
    HashMap<Integer, String> area_designacao = new HashMap<Integer, String>();

    /*codigo do concentrador e a sua porta*/
    HashMap<Integer, Integer> concentrador_porta = new HashMap<Integer, Integer>();

    //NAO SE USA AINDA PARA ESTA FASE
    /*codigo do concentrador e a sua lista de sensores*/
    HashMap<Integer, ArrayList<Integer>> concentrador_sensores = new HashMap<Integer, ArrayList<Integer>>();

    public boolean alterar_desig_area(int cod_area, String designacao) {
        int tamanho = designacao.length();
        if (tamanho > 20) {
            return false;
        }
        for (Integer i : area_designacao.keySet()) {
            if (i == cod_area) {
                area_designacao.put(cod_area, designacao);
            }
        }
        return true;
    }

    /*CORRETO*/
    public void adicionar_areaCsensores(int cod_area, int cod_s) {
        ArrayList<Integer> novo = new ArrayList();
        //System.out.println("********** Adicionar area_sensores ");
        if (area_sensores.containsKey(cod_area)) {
            /*novo fica com o arraylist de area_sensores*/
            novo.addAll(area_sensores.get(cod_area));
            novo.add(cod_s);
            area_sensores.put(cod_area, novo);
        } else {
            novo.add(cod_s);
            area_sensores.put(cod_area, novo);
        }
        //System.out.println("\t\tnovo : "+novo);
        //System.out.println("\t\tarea_sensores : "+area_sensores);
    }

    /*devolve a lista de sensores que a area tem*/
 /*CORRETO*/
    public ArrayList<Integer> sensores(int cod_area) {
        ArrayList<Integer> novo = new ArrayList();
        //System.out.println("********** Adicionar area_sensores ");
        if (area_sensores.containsKey(cod_area)) {
            /*novo fica com o arraylist de area_sensores*/
            novo.addAll(area_sensores.get(cod_area));
        }
        //System.out.println("\t\tnovo : "+novo);

        return novo;

    }

    /*devolte a area na qual o sensor esta*/
 /*a area e o sensor tem de comecar no 1 e aumentar consecutivamente*/
 /*CORRETO*/
    public int area(int cod_sensor) {
        //System.out.println("*********Areas");
        ArrayList<Integer> novo = new ArrayList();
        HashMap<Integer, ArrayList<Integer>> hnovo = new HashMap();
        hnovo.putAll(area_sensores);

        int i = 0;
        //System.out.println("SENSOR : "+cod_sensor);
        for (ArrayList<Integer> x : hnovo.values()) {
            i++;
            //System.out.println("i : "+i);
            novo = x;
            //System.out.println("novo : "+novo);
            for (Integer c : novo) {
                //System.out.println("valor : "+c);
                if (c == cod_sensor) {
                    //System.out.println("CORRESPONDE : "+c);
                    //System.out.println("cod_area : "+);
                    //System.out.println("AREA : "+i);
                    return i;
                }
            }
        }
        novo.clear();
        hnovo.clear();
        return 0;
    }

    /*CORRETO*/
    public void imprimir_sensores(int cod_area) {
        //System.out.println("*********Imprimir Sensores");
        ArrayList<Integer> imprimir = new ArrayList<>();
        imprimir = area_sensores.get(cod_area);
        if (imprimir == null) {
            System.out.println("0 sensores");
        } else {
            for (Integer obj : imprimir) {
                System.out.println(obj);
            }
        }
    }

    /*CORRETO*/
    public void TUDO_area_sensores() {
        System.out.println("\t\t" + area_sensores);
    }

    /*CORRETO*/
    public void adicionar_area_designacao(int cod_area, String designacao) {
        area_designacao.put(cod_area, designacao);
    }

    /*CORRETO*/
    public String designacao(int cod_area) {
        return area_designacao.get(cod_area);
    }

    /*CORRETO*/
    public void TUDO_area_designacao() {
        System.out.println("\t\t" + area_designacao);
    }

    /*CORRETO*/
    public void adicionar_concentradorPorta(int cod_concentrador, int nr_porta) {
        //System.out.println("********** Adicionar concentradorPorta ");        
        concentrador_porta.put(cod_concentrador, nr_porta);
        //System.out.println("\t\tconcentrador : "+concentrador_porta);
    }

    /*CORRETO*/
    public int concentrador(int nr_porta) {
        //System.out.println("*********Concentrador");
        HashMap<Integer, Integer> hnovo = new HashMap();
        hnovo.putAll(concentrador_porta);

        int i = 0;
        //System.out.println("Porta : "+nr_porta);
        for (Integer x : hnovo.values()) {
            i++;
            if (x == nr_porta) {
                /*System.out.println("CORRESPONDE : "+x);
                System.out.println("cod_concentrador : "+i);*/
                return i;
            }
        }
        hnovo.clear();
        return 0;
    }

    /*CORRETO*/
    public void TUDO_concentrador_porta() {
        System.out.println("\t\t" + concentrador_porta);
    }

    public void adicionar_concentradorCsensores(int cod_concentrador, int cod_sensor) {
        ArrayList<Integer> novo = new ArrayList();
        //System.out.println("********** Adicionar area_sensores ");
        if (cod_concentrador != 0) {
            if (concentrador_sensores.containsKey(cod_concentrador)) {
                /*novo fica com o arraylist de area_sensores*/
                novo.addAll(concentrador_sensores.get(cod_concentrador));
                novo.add(cod_sensor);
                concentrador_sensores.put(cod_concentrador, novo);
            } else {
                novo.add(cod_sensor);
                concentrador_sensores.put(cod_concentrador, novo);
            }
        }
        /*System.out.println("\t\tnovo : "+novo);
        System.out.println("\t\tarea_sensores : "+concentrador_sensores);*/
    }

    public void atualizar(Connection c) throws SQLException {
        Connection connection = c;

        Statement statement = null;
        statement = connection.createStatement();

        statement.executeUpdate("drop table if exists sensor");
        statement.executeUpdate("drop table if exists concentrador");
        statement.executeUpdate("drop table if exists area");

        statement.executeUpdate("create table Area(cod_area int check(cod_area>=0), designacao varchar(20) not null,primary key (cod_area))engine=innodb;");

        for (Integer i : area_designacao.keySet()) {
            statement.executeUpdate("insert into area values(" + i + ",'" + area_designacao.get(i) + "')");
        }
        TUDO_area_designacao();

        statement.executeUpdate("create table Concentrador(cod_concentrador int check(cod_concentrador>=0),nr_porta int not null check(nr_porta>=0),primary key(cod_concentrador))engine=innodb;");

        for (Integer i : concentrador_porta.keySet()) {
            statement.executeUpdate("insert into Concentrador values(" + i + "," + concentrador_porta.get(i) + ")");
        }
        TUDO_concentrador_porta();

        statement.executeUpdate("create table Sensor(cod_sensor int check(cod_sensor>=0),cod_area int not null check(cod_area>=0),cod_concentrador int,primary key(cod_sensor),foreign key(cod_area) references Area(cod_area) on update cascade on delete cascade,foreign key(cod_concentrador)references Concentrador(cod_concentrador)on update cascade on delete cascade)engine=innodb;");

        System.out.println(area_sensores);
        System.out.println(concentrador_sensores);

        for (Integer area : area_sensores.keySet()) {
            ArrayList<Integer> sensores_a = new ArrayList<Integer>();
            sensores_a = area_sensores.get(area);

            for (Integer sensor_a : sensores_a) {
                int encontrou = 0;
                for (Integer concentrador : concentrador_sensores.keySet()) {
                    ArrayList<Integer> sensores_c = new ArrayList<Integer>();
                    sensores_c = concentrador_sensores.get(concentrador);
                    for (Integer sensor_c : sensores_c) {
                        if (sensor_c == sensor_a) {
                            statement.executeUpdate("insert into Sensor values(" + sensor_c + "," + area + "," + concentrador + ")");
                            encontrou = 1;
                        }
                    }
                }
                if (encontrou == 0) {
                    statement.executeUpdate("insert into Sensor values(" + sensor_a + "," + area + ",null)");
                }
            }
        }

        System.out.println("---->Base de dados atualizada");

    }

    public int add_area(String designacao) {
        /*verificar se já existe*/
        if (designacao == null) {
            return 1;
        }
        int ultimo = 0;
        for (Integer area : area_designacao.keySet()) {
            ultimo = area;
            System.out.println("ultimo : " + ultimo);
        }

        area_designacao.put(ultimo + 1, designacao);
        return 0;
    }

    public boolean remover_area(/*int cod_area*/String designacao) {

        /*area_sensores.remove(cod_area);
        area_designacao.remove(cod_area);*/
        int area = 0;
        for (Integer a : area_designacao.keySet()) {
            if (area_designacao.get(a).equals(designacao)) {
                area = a;
                for (Integer i : area_sensores.keySet()) {
                    if (i == area) {
                        area_sensores.remove(i);
                        for (Integer ii : area_designacao.keySet()) {
                            if (ii == area) {
                                area_designacao.remove(ii);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void add_concentrador(int nr_porta) {
        int ultimo = 0;
        for (Integer concentrador : concentrador_porta.keySet()) {
            ultimo = concentrador;
            System.out.println("ultimo : " + ultimo);
        }

        concentrador_porta.put(ultimo + 1, nr_porta);
    }

    public void add_sensor() {

    }

    public boolean remover_sensor(int cod_area, int cod_sensor) {

        if (area_sensores.containsKey(cod_area)) {
            ArrayList<Integer> novo = new ArrayList<Integer>();
            novo = area_sensores.get(cod_area);
            System.out.println("novo : "+novo);
            int conta=0;
            for(Integer i:novo){
                System.out.println("i : "+i);
                if(i==cod_sensor){
                    System.out.println("encontrou");
                    novo.remove(conta);
                    area_sensores.put(cod_area, novo);
                    return true;
                }
                conta++;
            }            
        }
        
        return false;
    }

    public java.sql.Connection getConexaoMySQL() throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        System.out.println("----->CONEXÃO ESTABELECIDA");

        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String BD = "faseb";

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + BD, "root", null);
            if (connection != null) {
                status = ("STATUS--->Conectado com sucesso!");

            } else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }

            statement = connection.createStatement();
            resultset = statement.executeQuery("select *from area");
            while (resultset.next()) {
                adicionar_area_designacao(resultset.getInt("cod_area"), resultset.getString("designacao"));
            }

            resultset = statement.executeQuery("select *from sensor");
            while (resultset.next()) {
                if (resultset.getInt("cod_concentrador") == 0) {
                } else {
                }
                adicionar_areaCsensores(resultset.getInt("cod_area"), resultset.getInt("cod_sensor"));
                //AINDA NAO SE USA NESTA FASE
                adicionar_concentradorCsensores(resultset.getInt("cod_concentrador"), resultset.getInt("cod_sensor"));
            }

            //System.out.println(concentrador_sensores);
            /*System.out.println("Sensores da area 1 : ");
                imprimir_sensores(1);
                System.out.println(sensores(1));
                System.out.println("Sensores da area 2 : ");
                imprimir_sensores(2);
                System.out.println(sensores(2));
                System.out.println("Sensores da area 3 : ");
                imprimir_sensores(3);
                System.out.println(sensores(3));
                System.out.println("Sensores da area 4 : ");
                imprimir_sensores(4);
                System.out.println(sensores(4));
                TUDO_area_sensores();
                System.out.println("Area do sensor 1 : "+area(1));
                System.out.println("Area do sensor 2 : "+area(2));
                System.out.println("Area do sensor 3 : "+area(3));
                System.out.println("Area do sensor 4 : "+area(4));
                System.out.println("Area do sensor 5 : "+area(5));
                System.out.println("Area do sensor 6 : "+area(6));
                System.out.println("Area do sensor 7 : "+area(7));
                System.out.println("Area do sensor 8 : "+area(8));*/
            //System.out.println("\nConcentrador, Porta");
            resultset = statement.executeQuery("select *from concentrador");
            while (resultset.next()) {
                adicionar_concentradorPorta(resultset.getInt("cod_concentrador"), resultset.getInt("nr_porta"));
            }
            return connection;

        } catch (ClassNotFoundException e) {
            //System.out.println("ERRO: O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            //System.out.println("ERRO: Nao foi possivel conectar ao Banco de Dados.");
            return null;
            /*} finally {
            if (resultset != null) {
                resultset.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }*/
        }
    }

    public String statusConection() {
        return status;
    }

    public boolean FecharConexao() {
        try {
            this.getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public java.sql.Connection ReiniciarConexao() throws SQLException {
        FecharConexao();
        return this.getConexaoMySQL();
    }
}
