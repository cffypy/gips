package com.example.clover.generator;

/**
 * 代码生成
 */
public class MpGenerator {

    public static void main(String[] args) {
        String sourcePath = MpGenerator.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (sourcePath.endsWith("target/classes/")) {
            sourcePath = sourcePath.substring(0, sourcePath.lastIndexOf("target/classes/"));
        }
        sourcePath = sourcePath + "src/main/java/";
        System.out.println("代码生成路径：" + sourcePath);
        MpGeneratorUtils.generate(sourcePath, "com.example.clover", new String[]{"ec_user"});
    }

}
