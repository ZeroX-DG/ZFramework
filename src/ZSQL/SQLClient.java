/*
 * The MIT License
 *
 * Copyright 2017 Nguyen Viet Hung.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ZSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLClient {

    private Connection conn;
    private String _query = "";
    private String _select = "";
    private String _from = "";
    private String _where = "";
    private String _order = "";
    private String _top = "";
    private String _insert = "";
    private String _insert_column = "";
    private String _insert_values = "";
    private String _update = "";
    private String _update_set = "";
    private String _delete = "";
    // <editor-fold defaultstate="collapsed" desc="Constructors">

    /**
     * Create a new SQLClient base on:<br />
     * Engine, host, port, name, pass, database name
     *
     * @param engine an String that represents the database engine
     * @param host an String that represents the host name
     * @param port an Int that represents the port to connect
     * @param name an String that represents the username
     * @param pass an String that represents the password
     * @param database an String that represents the database name
     */
    public SQLClient(String engine, String className, String host, int port, String name, String pass, String database) throws Exception {
        Class.forName(className);
        String connectionString = "jdbc:" + engine + "://" + host + ":" + port + "/" + database;
        conn = DriverManager.getConnection(connectionString, name, pass);
    }

    /**
     * Create a new SQLClient base on:<br />
     * Engine, Host, Name, Pass, Database
     */
    public SQLClient(String engine, String className, String host, String name, String pass, String database) throws Exception {
        Class.forName(className);
        String connectionString = "jdbc:" + engine + "://" + host + "/" + database;
        conn = DriverManager.getConnection(connectionString, name, pass);
    }

    /**
     * Create a new SQLClient base on:<br />
     * Engine, Connection String
     */
    public SQLClient(String engine, String className, String connectionString) throws Exception {
        Class.forName(className);
        conn = DriverManager.getConnection(connectionString);
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Functions">
    /**
     * Indicate tables to select from
     *
     * @param tables list of table names
     * @return The class for function chaining
     */
    public SQLClient from(String... tables) {
        this._from = " FROM ";
        for (String table : tables) {
            _from += table + ", ";
        }
        _from = _from.substring(0, _from.length() - 2);
        return this;
    }

    /**
     * Indicate columns to select
     *
     * @param columns list of column names
     * @return The class for function chaining
     */
    public SQLClient select(String... columns) {
        _select = "SELECT ";
        for (String column : columns) {
            _select += column + ", ";
        }
        _select = _select.substring(0, _select.length() - 2);
        return this;
    }

    private boolean whereWithOperator(String[] conditions) {
        for (String condition : conditions) {
            //_where += " " + condition;
            if (condition.equalsIgnoreCase("=")
                    || condition.equalsIgnoreCase(">=")
                    || condition.equalsIgnoreCase("<=")
                    || condition.equalsIgnoreCase("!=")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public SQLClient where(String... conditions) {
        if (_where.isEmpty()) {
            _where += " WHERE";
        }
        if (whereWithOperator(conditions)) {
            //example parameter: "a", "=", "b"
            int parameters = 0;
            for (String condition : conditions) {
                ++parameters;
                switch (parameters) {
                    case 1:
                        //first column always string
                        _where += " AND " + condition;
                        break;
                    case 2:
                        //second column always operator
                        if (condition.equalsIgnoreCase("=")
                                || condition.equalsIgnoreCase(">=")
                                || condition.equalsIgnoreCase("<=")
                                || condition.equalsIgnoreCase("!=")) {
                            _where += " " + condition;
                        }
                        break;
                    case 3:
                        //maybe string or number
                        if (isNumeric(condition)) {
                            _where += " " + condition;
                        } else {
                            _where += " '" + condition + "'";
                        }
                        parameters = 0;
                        break;
                    default:
                        break;
                }
            }
        } else {
            for (String condition : conditions) {
                _where += " " + condition;
            }
        }
        if (_where.substring(7, 11).equalsIgnoreCase("and ")) {
            _where = " WHERE " + _where.substring(11, _where.length());
        }
        return this;
    }

    public String getQuery() {
        build();
        return _query;
    }

    public boolean isConnected() {
        return conn != null;
    }

    private String buildSQL() {
        if (!_top.isEmpty()) {
            _select = "SELECT" + _top + _select.substring(6, _select.length());

        }
        _query += _select + _from + _where + _order; //+ _limit;
        return _query;
    }

    public ResultSet get(String sql) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet get() {
        buildSQL();
        try {
            PreparedStatement ps = conn.prepareStatement(_query);
            ResultSet rs = ps.executeQuery();
            clearAll();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            clearAll();
            return null;
        }
    }

    private void buildInsert() {
        _insert_column = _insert_column.substring(0, _insert_column.length() - 2);
        _insert_column += ")";
        _insert_values = " VALUES (" + _insert_values;
        _insert_values = _insert_values.substring(0, _insert_values.length() - 2) + ")";
        _query += _insert + _insert_column + _insert_values;
    }

    public SQLClient insert(String table) {
        _insert += "INSERT INTO " + table + "(";
        return this;
    }

    public SQLClient update(String table) {
        _update += "UPDATE " + table + " SET ";
        return this;
    }

    public SQLClient set(String column, String value) {
        if (!_insert.isEmpty()) {
            //insert
            _insert_column += column + ", ";
            _insert_values += "N'" + value + "', ";
            return this;
        } else {
            _update_set += column + " = N'" + value + "', ";
            return this;
        }
    }

    public SQLClient set(String column, int value) {
        if (!_insert.isEmpty()) {
            //insert
            _insert_column += column + ", ";
            _insert_values += value + ", ";
            return this;
        } else {
            _update_set += column + " = " + value + ", ";
            return this;
        }
    }

    private SQLClient buildUpdate() {
        _query += _update + _update_set.substring(0, _update_set.length() - 2) + _where;
        return this;
    }

    private void build() {
        if (!_insert.isEmpty()) {
            buildInsert();
        } else if (!_select.isEmpty()) {
            buildSQL();
        } else if (!_delete.isEmpty()){
            buildDelete();
        } else {
            buildUpdate();
        }
    }

    public boolean run(String sql) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            int status = ps.executeUpdate();
            if (status > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean run() {
        build();
        try {
            PreparedStatement ps = conn.prepareStatement(_query);
            int status = ps.executeUpdate();
            if (status > 0) {
                clearAll();
                return true;
            } else {
                clearAll();
                return false;
            }
        } catch (Exception e) {
            clearAll();
            e.printStackTrace();
            return false;
        }
    }

    private void clearAll() {
        _query = "";
        _select = "";
        _from = "";
        _where = "";
        _order = "";
        _top = "";
        _insert = "";
        _insert_column = "";
        _insert_values = "";
        _update = "";
        _update_set = "";
        _delete = "";
    }

    public SQLClient orderBy(String column, String order) {
        _order += " order by " + column + " " + order;
        return this;
    }

    public SQLClient deleteFrom(String table) {
        _delete += table;
        return this;
    }
    
    private void buildDelete(){
        _query += "DELETE FROM " + _delete + _where;
    }
    //</editor-fold>
}
