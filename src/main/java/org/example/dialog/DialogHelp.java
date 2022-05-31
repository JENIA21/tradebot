package org.example.dialog;

import org.example.commands.Response;

public class DialogHelp extends Dialog {

    @Override
    public String execute() {return Response.HELP.toString();}


}
