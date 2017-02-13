package com.aerolinea.reportes;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean
@ViewScoped
public class controlReporteVuelos {

    private Date fecha1;
    private Date fecha2;
    private Connection conn;
    JasperPrint jasperPrint;
    HttpServletResponse httpServletResponse;

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    @PostConstruct
    public void init() {
        ConexionPool c = new ConexionPool();
        c.conectar();
        conn = c.getConexion();
    }

    public controlReporteVuelos() {
    }

    public void generarReporte(String tipo) throws JRException, IOException {

        if (tipo.equals("pdf")){
                ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String reportePath = sc.getRealPath("reportes/ReporteVuelos.jasper");
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("fecha1", fecha1);
                parametros.put("fecha2", fecha2);
                parametros.put(JRParameter.REPORT_LOCALE, new Locale("es", "ES"));
                jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
                httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setHeader("Content-Disposition", "inline;filename=ReporteVuelos.pdf");
                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
                FacesContext.getCurrentInstance().responseComplete();
        }else if (tipo.equals("doc")){
            
        }else if (tipo.equals("xls")){
            
        }
    }
}
