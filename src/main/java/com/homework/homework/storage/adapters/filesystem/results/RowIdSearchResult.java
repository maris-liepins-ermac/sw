package com.homework.homework.storage.adapters.filesystem.results;

public class RowIdSearchResult {
    private boolean isFound;
    private long rowId;

    public RowIdSearchResult(boolean isFound, long id) {
        this.isFound = isFound;
        this.rowId = id;
    }

    public boolean isFound() {
        return isFound;
    }

    public long getRowId() {
        return rowId;
    }
}
