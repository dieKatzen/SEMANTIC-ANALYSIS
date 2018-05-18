package SymbolTables;

import java.util.ArrayList;

public class BaseTable {

    String tableName;
    ArrayList<TableRow> tableRows = new ArrayList<TableRow>();

    public BaseTable(String tableName, ArrayList<TableRow> tableRows) {
        this.tableName = tableName;
        this.tableRows = tableRows;
    }

    public BaseTable(String tableName) {
        this.tableName = tableName;
    }



    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<TableRow> getTableRows() {
        return tableRows;
    }

    public void setTableRows(ArrayList<TableRow> tableRows) {
        this.tableRows = tableRows;
    }

}
