package com.wanted.only_one.global.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class QueryUtil {

    private static final Map<String, String> queries = new HashMap<>();

    // XML에서 쿼리를 로드하는 정적 블록
    static {
        loadQueries();
    }

    private static void loadQueries() {
        try {
            InputStream StudyinputStream = QueryUtil.class.getClassLoader().getResourceAsStream("study_queries.xml");
            InputStream MemberinputStream = QueryUtil.class.getClassLoader().getResourceAsStream("member_queries.xml");
            InputStream CourseinputStream = QueryUtil.class.getClassLoader().getResourceAsStream("queries.xml");
            InputStream PaymentsinputStream = QueryUtil.class.getClassLoader().getResourceAsStream("payments_queries.xml");

            if (StudyinputStream == null) {
                throw new RuntimeException("study_queries.xml 파일을 찾을 수 없습니다.");
            }
            if (MemberinputStream == null) {
                throw new RuntimeException("member_queries.xml 파일을 찾을 수 없습니다.");
            } if (CourseinputStream == null) {
                throw new RuntimeException("course_queries.xml 파일을 찾을 수 없습니다.");
            }
            if (PaymentsinputStream == null) {
                throw new RuntimeException("payments_queries.xml 파일을 찾을 수 없습니다.");
            }


            // DocumentBuilderFactory를 사용하여 DocumentBuilder 인스턴스를 생성
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // InputStream으로부터 XML 문서를 파싱하여 Document 객체 생성
            Document Studydocument = builder.parse(StudyinputStream);
            Document Memberdocument = builder.parse(MemberinputStream);
            Document Coursedocument = builder.parse(CourseinputStream);
            Document Paymentsdocument = builder.parse(PaymentsinputStream);
            // 문서의 구조를 정규화 (노드의 표준 형태로 변환)
            Studydocument.getDocumentElement().normalize();
            Memberdocument.getDocumentElement().normalize();
            Coursedocument.getDocumentElement().normalize();
            Paymentsdocument.getDocumentElement().normalize();
            // "query" 태그를 가진 모든 노드를 가져옴
            NodeList StudynodeList = Studydocument.getElementsByTagName("query");
            NodeList MembernodeList = Memberdocument.getElementsByTagName("query");
            NodeList CoursenodeList = Coursedocument.getElementsByTagName("query");
            NodeList PaymentsnodeList = Paymentsdocument.getElementsByTagName("query");

            // 각 "query" 노드를 반복하여 처리
            for (int i = 0; i < StudynodeList.getLength(); i++) {
                Element queryElement = (Element) StudynodeList.item(i);
                String key = queryElement.getAttribute("key");
                String sql = queryElement.getTextContent().trim();
                queries.put(key, sql);
            }

            for (int i = 0; i < MembernodeList.getLength(); i++) {
                Element queryElement = (Element) MembernodeList.item(i);
                String key = queryElement.getAttribute("key");
                String sql = queryElement.getTextContent().trim();
                queries.put(key, sql);
            }

            for (int i = 0; i < CoursenodeList.getLength(); i++) {
                Element queryElement = (Element) CoursenodeList.item(i);
                String key = queryElement.getAttribute("id");
                String sql = queryElement.getTextContent().trim();
                queries.put(key, sql);
            }

            for (int i = 0; i < PaymentsnodeList.getLength(); i++) {
                Element queryElement = (Element) PaymentsnodeList.item(i);
                String key = queryElement.getAttribute("key");
                String sql = queryElement.getTextContent().trim();
                queries.put(key, sql);
            }
        } catch (Exception e) {
            // 예외 발생 시 RuntimeException으로 감싸서 다시 던짐
            throw new RuntimeException("쿼리 로딩 중 오류 발생", e);
        }
    }

    public static String getQuery(String key) {
        return queries.get(key);
    }
}