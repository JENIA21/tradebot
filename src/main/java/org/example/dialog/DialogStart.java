package org.example.dialog;

import org.example.commands.Response;

public class DialogStart extends Dialog {
    @Override
    public String execute() {
        return Response.START.toString();
    }
}
