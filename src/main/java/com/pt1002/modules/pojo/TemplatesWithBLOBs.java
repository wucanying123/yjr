package com.pt1002.modules.pojo;

public class TemplatesWithBLOBs extends Templates {
    private byte[] template;

    private byte[] hiRes;

    private byte[] hiResTex;

    private byte[] sample;

    private byte[] models;

    private boolean bTemplate;
    private boolean bHiRes;
    private boolean bHiResTex;

    public boolean isbTemplate() {
        return bTemplate;
    }

    public void setbTemplate(boolean bTemplate) {
        this.bTemplate = bTemplate;
    }

    public boolean isbHiRes() {
        return bHiRes;
    }

    public void setbHiRes(boolean bHiRes) {
        this.bHiRes = bHiRes;
    }

    public boolean isbHiResTex() {
        return bHiResTex;
    }

    public void setbHiResTex(boolean bHiResTex) {
        this.bHiResTex = bHiResTex;
    }

    public byte[] getTemplate() {
        return template;
    }

    public void setTemplate(byte[] template) {
        this.template = template;
    }

    public byte[] getHiRes() {
        return hiRes;
    }

    public void setHiRes(byte[] hiRes) {
        this.hiRes = hiRes;
    }

    public byte[] getHiResTex() {
        return hiResTex;
    }

    public void setHiResTex(byte[] hiResTex) {
        this.hiResTex = hiResTex;
    }

    public byte[] getSample() {
        return sample;
    }

    public void setSample(byte[] sample) {
        this.sample = sample;
    }

    public byte[] getModels() {
        return models;
    }

    public void setModels(byte[] models) {
        this.models = models;
    }
}