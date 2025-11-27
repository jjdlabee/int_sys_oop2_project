/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.intellectual_systems.reporting;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Jonathan
 */
public class DocxReportGenerator implements ReportGenerator {

    @Override
    public void generateReport(String filePath, String content) {
        // Implementation for generating DOCX report
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(content);

            try (var out = new java.io.FileOutputStream(filePath)) {
                document.write(out);
            }

        } catch (Exception e) {
            System.err.println("Error generating DOCX report: " + e.getMessage());
        }
    }
}
