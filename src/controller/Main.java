/*
 * Copyright (C) 2017 Roger G. Coscojuela
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JFrame;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;
import vista.gui;

public class Main {

    private static final String ERROR_FILE = "error.log";
    private final Properties props = new Properties();

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.inici();
    }

    public void inici() {
        try {
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            InputStream in = this.getClass().getResourceAsStream("/system.conf");
            props.load(in);
            JFrame gui = new gui();
            gui.setLocation(gd.getDisplayMode().getWidth() - gui.getWidth(), gd.getDisplayMode().getHeight() - gui.getHeight() - 40);
            gui.setTitle("Notas 2.0");
            gui.setVisible(true);
        } catch (NullPointerException | IOException ex) {
            //An error has ocurred, and the program must not run.
            boolean errorLogged = false;
            PrintWriter pout = null;
            try {
                pout = new PrintWriter(new FileWriter(ERROR_FILE, true));
                pout.print((System.getProperty("line.separator") + "********" + Calendar.getInstance().getTime().toString() + "********" + System.getProperty("line.separator")));
                ex.printStackTrace(pout);
                //System.out.println(ex.printStackTrace());
                System.err.println("Error logged");
                errorLogged = true;
            } catch (IOException ex1) {
                System.err.println("Fatal error! We cannot even log it!");
            } finally {
                if (pout != null) {
                    pout.close();
                }
                String error;
                if (errorLogged) {
                    error = "Error loading conf file. \nSee "+ERROR_FILE+ " for details.";
                } else {
                    error = "Error loading conf file. \nFurthermore, no log can be generated.\n\nAre you root?";
                }
                ErrorInfo info = new ErrorInfo("Fatal Error", error, ex.getMessage(), error, ex, Level.SEVERE, null);
                JXErrorPane.showDialog(null, info);
            }

        }
    }

}
