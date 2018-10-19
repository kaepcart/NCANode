package kz.ncanode.log;

import kz.ncanode.Helper;
import kz.ncanode.config.ConfigServiceProvider;
import kz.ncanode.ioc.ServiceProvider;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class RequestLogServiceProvider extends Log implements ServiceProvider {
    OutLogServiceProvider out = null;
    ConfigServiceProvider config = null;

    private String requestLogFile = "";

    public RequestLogServiceProvider(ConfigServiceProvider config, OutLogServiceProvider out) {

        this.out = out;
        this.config = config;

        try {
            requestLogFile = this.config.get("log", "request_log");
            requestLogFile = Helper.absolutePath(requestLogFile);

            os = new FileOutputStream(requestLogFile);
            ps = new LogPrintStream(os);
        } catch (FileNotFoundException e) {
            this.out.write("WARNING! Cannot create request log file at: " + requestLogFile);
        }
    }
}
