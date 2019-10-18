package com.bjsxt.ego.beans;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lvyelanshan
 * @create 2019-10-18 20:20
 */
public class FtpUtils {
    public static void main(String[] args) {
        /**
         * 完成图片的上传，通过ftp将图片上传到图片服务器
         */
        String hostname = "192.168.43.131";
        int port = 21; //linux主机中配置的vsftpd服务通过21端口访问
        String username = "ftpuser";
        String password = "ftpuser";
        String pathname = "/home/ftpuser/jd";
        //把文件上传到服务器后的名字
        String remote = "demo.jpg";
        InputStream local = null;



//        uploadFile(hostname, username, password, pathname, remote, client,local);

    }
    public static boolean uploadFile(String hostname,
                                     int port, String username,
                                     String password, String pathname,
                                     String remote,InputStream local) {

        boolean flag=false;

        try{
            //创建FtpClient对象
            FTPClient client=new FTPClient();
            //建立和ftp服务器的链接
            client.connect(hostname, port);
            //登陆ftp服务器
            client.login(username, password);
            //设置上传的文件的类型
            client.setFileType(FTP.BINARY_FILE_TYPE);
            //切换工作目录，文件上传后保存到那个目录
            if(!client.changeWorkingDirectory(pathname)){
                if(client.makeDirectory(pathname)){
                    client.changeWorkingDirectory(pathname);
                }
            }

//            local=new FileInputStream("D:/1.jpg");
            //实现文件上传
            client.storeFile(remote, local);

            local.close();

            client.logout();
            client.disconnect();
            flag=true;

        }catch(Exception ex){
            ex.printStackTrace();
        }

        return flag;


    }
}
