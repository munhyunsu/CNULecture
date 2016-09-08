package org.cscnu.hobby;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    public static void main(String[] args) {
        /** 
         * 파일 포인터 지정
         * 파일 읽기로 열기
         * 참고 - pg1342.txt는 gutenberg project
         *        에서 다운로드 받은 텍스트 파일
         *        1500 Bytes 보다 큰 데이터 파일
         */
	    File file = new File("pg1342.txt");
        FileInputStream fileInputSteam;

        try {
            /**
             * 파일 읽기로 열기
             * 파일 채널 열기
             */
            fileInputSteam = new FileInputStream(file);
            FileChannel fileChannel = fileInputSteam.getChannel();

            /**
             * 파일 채널에서 데이터를 읽기 위한 변수
             */
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1500);

            /**
             * 데이터를 읽어오기
             * 만약 FileChannel이 가지고 있는 특성으로
             * 오류가 생기는 것이라면, readBytes에는 
             * 576 이상의 숫자가 들어갈 수 없음
             * 참고 - readBytes에는 읽어온 데이터의 크기
             *        가 저장됨
             */
            int readBytes = 0;
            readBytes = fileChannel.read(byteBuffer);

            /**
             * 읽어온 데이터의 크기 출력
             */
            System.out.println("ReadBytes: \t" + 
                    Integer.toString(readBytes));

            /**
             * 읽어온 데이터 출력
             */
            if(readBytes > 0) {
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                System.out.print(new String(bytes));
            }
        } catch (IOException e) { /** IO Error Catch */
            e.printStackTrace();
        }
    }
}
