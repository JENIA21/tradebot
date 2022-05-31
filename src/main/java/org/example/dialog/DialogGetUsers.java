package org.example.dialog;

import org.example.DbControl;
import org.example.commands.Response;

import java.sql.SQLException;

public class DialogGetUsers extends Dialog {

    @Override
    public String execute() throws SQLException {

        return DbControl.getInstance().getAllUsers().toString();
    }


}
