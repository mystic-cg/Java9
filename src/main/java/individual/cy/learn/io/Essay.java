﻿package individual.cy.learn.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author mystic
 * @date 2017/12/26 9:04
 */
public class Essay {

    private static boolean isOkay = false;

    public static boolean copyFile(File source, File target) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(target))) {
            int len;
            byte[] bytes = new byte[1024];
            while ((len = bis.read()) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean copy(File source, File target) {
        // create file path
        File newFile = new File(target, source.getName());
        // this "target" is only as a directory
        if (!target.exists()) {
            isOkay = target.mkdirs();
        }
        if (source.isDirectory()) {
            isOkay = newFile.mkdirs();
            File[] files = source.listFiles();
            for (File file : Objects.requireNonNull(files)) {
                isOkay = copy(file, newFile);
            }
        } else {
            isOkay = copy(source, newFile);
        }
        return isOkay;
    }

}
