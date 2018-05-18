package SymbolTables;

public class TableRow {
    String rowName;

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getRowKind() {
        return rowKind;
    }

    public void setRowKind(String rowKind) {
        this.rowKind = rowKind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BaseTable getLink() {
        return link;
    }

    public void setLink(BaseTable link) {
        this.link = link;
    }

    String rowKind;
    int offset;
    String type;

    public TableRow(String rowName, String rowKind, String type, BaseTable link) {
        this.rowName = rowName;
        this.rowKind = rowKind;
        this.type = type;
        this.link = link;
    }

    public TableRow(String rowName, String rowKind, String type, BaseTable link, int os) {
        this.rowName = rowName;
        this.rowKind = rowKind;
        this.type = type;
        this.link = link;
        this.offset = os;
    }

    BaseTable link;


    public enum RowKind{FUNCTION,CLASS,PARAMETER,VARIABLE}
}
