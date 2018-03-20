package SymbolTables;

public class TableRow {
    String rowName;
    RowKind rowKind;
    String type;

    public TableRow(String rowName, RowKind rowKind, String type, BaseTable link) {
        this.rowName = rowName;
        this.rowKind = rowKind;
        this.type = type;
        this.link = link;
    }

    BaseTable link;


    public enum RowKind{FUNCTION,CLASS,PARAMETER,VARIABLE}
}
