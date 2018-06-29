package com.example.demo.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.GregorianCalendar;

import static java.lang.String.format;

@Component
class DemoRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("quartz2://demo/execute?cron=0+0/1+*+?+*+*")
                .routeId("DEMO_QUARTZ2")
                .description("every minute")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) {
                        System.out.println();
                    }
                })
        ;
        from("file:/home/timur/downloads?noop=true")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println();
                    }
                })
                .to(getSftpPath())
                ;
    }

    private String getSftpPath() {

        return format("sftp://%1$s@%2$s:%3$s%4$s?password=RAW(%5$s)&binary=true&"
                        + "maximumReconnectAttempts=5&reconnectDelay=12000&"
                        + "soTimeout=60000&disconnect=true",

                "letobank",
                "sftp.mycardif.ru", 22,
                "/Ежедневные реестры СКП",
                "Sk7$_&!40U3&}l2U");
    }
}
