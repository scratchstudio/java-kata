package com.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * java.lang.String 클래스 테스트
 */
public class SwitchCaseTest {
    @Before
    public void setUp() {

    }

    @Test
    public void 자바8_스위치케이스_스트링_타입_처리() {
        String day = "FRI";
        String message = "";

        switch (day) {
            case "MON":
                message = "월요일";
                break;
            case "TUE":
                message = "화요일";
                break;
            case "WED":
                message = "수요일";
                break;
            case "THU":
                message = "목요일";
                break;
            case "FRI":
                message = "금요일";
                break;
        }

        assertEquals("금요일", message);
    }
}

// 자바8에서 스트링 타입을 switch-case의 인자로 받을 때, JVM의 처리 방식을 아래의 디컴파일 된 코드 참조

// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
//            @Test
//            public void 자바8_스위치케이스_스트링_타입_처리() {
//                String day = "FRI";
//                String message = "";
//                byte var4 = -1;
//                switch(day.hashCode()) {
//                    case 69885:
//                        if(day.equals("FRI")) {
//                            var4 = 4;
//                        }
//                        break;
//                    case 76524:
//                        if(day.equals("MON")) {
//                            var4 = 0;
//                        }
//                        break;
//                    case 83041:
//                        if(day.equals("THU")) {
//                            var4 = 3;
//                        }
//                        break;
//                    case 83428:
//                        if(day.equals("TUE")) {
//                            var4 = 1;
//                        }
//                        break;
//                    case 85814:
//                        if(day.equals("WED")) {
//                            var4 = 2;
//                        }
//                }
//
//                switch(var4) {
//                    case 0:
//                        message = "월요일";
//                        break;
//                    case 1:
//                        message = "화요일";
//                        break;
//                    case 2:
//                        message = "수요일";
//                        break;
//                    case 3:
//                        message = "목요일";
//                        break;
//                    case 4:
//                        message = "금요일";
//                }