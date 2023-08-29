package com.VirtualCard.maplerads.Exception;

public class ExpiredJwtException {
    private String message;

    public ExpiredJwtException(String msg){
        this.message = msg;
    }
}
