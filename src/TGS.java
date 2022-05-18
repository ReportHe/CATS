import pack.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import des.MainBody;
import struct.JavaStruct;
import struct.StructException;
import javax.net.ssl.CertPathTrustManagerParameters;

public class TGS implements Runnable {
        private Socket socket2;
        private static byte[] packageReceived;
        static String KEY = "12345678";
        private String IDtgs;
        private String KCV;
        private String IDV;
        private String Lifetime4 = "10s";
        private String TS4;
        private String TS;

        public TGS(Socket socket) {
                this.socket2 = socket;
                this.IDtgs = "172.20.10.7"; // TGS服务器IP地址：172.24.32.1
                this.KCV = "12345678";
                this.IDV = "172.20.10.7"; // 服务器IP地址
        }

        @Override
        public void run() {
                int len = 0, TmpArraySize = 0;
                byte[] buffer = new byte[1024];
                ArrayList<Byte> TmpArray = new ArrayList<Byte>();

                try {
                        while ((len = socket2.getInputStream().read(buffer)) != -1) { // 将套接字中的内容读到buffer中
                                for (int i = 0; i < len; i++) {
                                        TmpArray.add(buffer[i]); // ArrayList类的add方法将buffer中的数据添加到ArrayList
                                }
                        }
                        TmpArraySize = TmpArray.size();
                        packageReceived = new byte[TmpArraySize];
                        for (int i = 0; i < TmpArraySize; i++) {
                                packageReceived[i] = TmpArray.get(i);
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }

                PackageCtoTgs packagectotgs = new PackageCtoTgs("", new byte[] { (byte) 0 }, new byte[] { (byte) 0 });
                try {
                        byte[] TGS_ticket = null;
                        byte[] TGS_authenticator = null;
                        TicketTgs tickettgs = new TicketTgs("", "", "", "", "", "");
                        Authenticator authenticator = new Authenticator("", "", "");

                        // 解整个包
                        JavaStruct.unpack(packagectotgs, packageReceived); // 将packageReceived字节流解包成packageCtoTgs结构体形式

                        // 提取packagectotgs中的TicketTgs和Authenticator部分并解密到字符数组中
                        TGS_ticket = new MainBody(packagectotgs.TicketTgs, KEY, 0).mainBody(); // TGS解密从Client发送过来的包的票据部分
                        TGS_authenticator = new MainBody(packagectotgs.Authenticator, new String(tickettgs.KcTgs), 0)
                                        .mainBody(); // TGS解密从Client发送过来的包的Authenticator部分

                        // 解整包中的TGS_ticket和TGS_authenticator部分
                        JavaStruct.unpack(tickettgs, TGS_ticket); // 将解密后的票据部分的字节流形式解包成TicketTgs结构体形式
                        JavaStruct.unpack(authenticator, TGS_authenticator);

                        System.out.println(String.valueOf(tickettgs.IdClient) + "\t"
                                        + String.valueOf(authenticator.IdClient)
                                        + "\t"
                                        + String.valueOf(tickettgs.AddressClient) + "\t"
                                        + String.valueOf(authenticator.AddressClient));

                        if (Arrays.equals(tickettgs.IdClient, authenticator.IdClient)
                                        && Arrays.equals(tickettgs.AddressClient, authenticator.AddressClient))// 比对票据和认证中的信息是否核对成功，成功则发送包
                        {
                                log.Log.AuthLog(IDtgs, 0, String.valueOf(tickettgs.IdClient));
                                System.out.println("用户认证成功!");

                                //打包票据并加密
                                Date date1 = new Date(0);   //返回自1970年1月1日零时以来的毫秒数
                                SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                TS = df1.format(date1);   //以上面的格式输出字符串形式的时间戳
                                System.out.println(IDV);
                                TicketV a2 = new TicketV(KCV, String.valueOf(tickettgs.IdClient),
                                                String.valueOf(tickettgs.AddressClient), IDV, TS,
                                                Lifetime4); //
                                byte[] a2fb = JavaStruct.pack(a2);
                                byte[] a2jiami = new MainBody(a2fb, KEY, 1).mainBody();

                                //
                                Date date = new Date(0);
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                TS4 = df.format(date);
                                PackageTgstoCEkCTgs d2 = new PackageTgstoCEkCTgs(KCV, IDV, TS4, a2jiami);
                                System.out.println(String.valueOf(d2.IDv));
                                System.out.println(String.valueOf(a2jiami));

                                byte[] d2fb = JavaStruct.pack(d2);
                                byte[] d2jiami = new MainBody(d2fb, String.valueOf(tickettgs.KcTgs), 1).mainBody();
                                PackageTgstoC c2 = new PackageTgstoC(d2jiami);
                                byte[] c2fb = JavaStruct.pack(c2);
                                System.out.println(Arrays.toString(c2fb));

                                socket2.getOutputStream().write(c2fb);
                                socket2.shutdownOutput();
                        } else {
                                log.Log.AuthLog(IDtgs, 1, String.valueOf(tickettgs.IdClient));
                                System.out.println("非法入侵！");
                        }
                } catch (IOException | StructException e) {
                        e.printStackTrace();
                }
        }

        public static void main(String[] args) throws Exception {
                ServerSocket serverSocket;
                byte[] buffer = new byte[1024];

                try {
                        serverSocket = new ServerSocket(8888); // 绑定到端口8888的TGS服务器套接字
                        System.out.println("TGS服务器启动");
                        while (true) {
                                Socket socket = serverSocket.accept();
                                System.out.println("用户已连接");
                                new Thread(new TGS(socket)).start(); // start()会调用重写的run方法
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}