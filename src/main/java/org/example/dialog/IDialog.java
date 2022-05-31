package org.example.dialog;
import java.io.IOException;
import java.sql.SQLException;


public interface IDialog {
    String execute() throws IOException, SQLException;
}