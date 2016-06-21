package com.clement.magichome;

public class ActionResult {

    private final long id;
    private final String content;

    public ActionResult(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}