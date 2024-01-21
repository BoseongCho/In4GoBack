package com.boseongcho.in4goback.common;

import org.springframework.stereotype.Component;

@Component
public class ConvertBytes {

    public String bytesToKilobytes(long bytes){
        double kilo = (double) bytes / 1024;
        String twoDecial = String.format("%.2f", kilo);
        twoDecial = twoDecial + "KB";

        return twoDecial;
    }

    public String bytesToMegabytes(long bytes){
        double mega = (double) bytes / (1024 * 1024 );
        String twoDecial = String.format("%.2f", mega);
        twoDecial = twoDecial + "MB";

        return twoDecial;
    }
}
