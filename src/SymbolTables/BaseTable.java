package SymbolTables;

import java.util.ArrayList;

public class BaseTable {
    public BaseTable(String tableName, ArrayList<TableRow> tableRows) {
        this.tableName = tableName;
        this.tableRows = tableRows;
    }

    String tableName;
    ArrayList<TableRow> tableRows;
}
