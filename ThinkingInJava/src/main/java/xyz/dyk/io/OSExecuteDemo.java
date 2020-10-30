package xyz.dyk.io;
//  Demonstrates standard I/O redirection.

import net.mindview.util.OSExecute;

public class OSExecuteDemo {
    public static void main(String[] args) {
        //OSExecute.command("cd src");
       // OSExecute.command("cd xyz.dyk.io");
        OSExecute.command("javap xyz.dyk.io.OSExecuteDemo");
    }
}
