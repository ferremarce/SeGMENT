/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import segment.fachada.ExpedienteFacade;
import util.JSFutil;

/**
 *
 * @author root
 */
@Named(value = "DownloadFile")
@SessionScoped
public class DownloadFile implements Serializable {

    private static final Logger LOG = Logger.getLogger(DownloadFile.class.getName());

    @Inject
    ExpedienteFacade expedienteFacade;

    private String pagina;
    private Integer id;
    private String mensaje = "";

    /**
     * Creates a new instance of DownloadFile
     */
    public DownloadFile() {
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void init() throws IOException {
        String noContent = "<html><h1>Sin adjunto...</></html>";
        this.mensaje = "";
        try {
            /**
             * Formato del URL q=pagina/valor1/valor2....
             */
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
            String url = paramMap.get("file");
            String[] arrayUrl = url.split("-");
            pagina = arrayUrl[0];
            id = Integer.parseInt(arrayUrl[1]);
            //Imprimir las peticiones en el log
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LOG.log(Level.INFO, "------------- DESCARGANDO ARCHIVO: {0} /descarga/{1} desde la IP: {2}", new Object[]{pagina, url, JSFutil.getClientIpAddr(request)});

            StreamedContent content = new DefaultStreamedContent(new ByteArrayInputStream(noContent.getBytes()), "text/html", "No existe Archivo");
            switch (pagina) {
                case "expediente": //INICITIVAS DE EXPEDIENTES
                    content = this.downloadExpediente(id);
                    break;
                default:
                    break;
            }
            if (content == null) {
                throw new ExcepcionManager("Archivo no encontrado..");
            }

            //Preparar la descarga
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType(content.getContentType());
            response.setHeader("Content-disposition", "inline; filename=" + content.getName());

            byte[] buffer = new byte[2048];
            int length;

            InputStream inputStream = content.getStream();
            OutputStream output = response.getOutputStream();
            while ((length = (inputStream.read(buffer))) >= 0) {
                output.write(buffer, 0, length);
            }
            inputStream.close();
            output.close();

            response.setStatus(200);
            context.responseComplete();

        } catch (NumberFormatException | IOException | ExcepcionManager e) {
//            FacesContext context = FacesContext.getCurrentInstance();
//            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//            PrintWriter out = response.getWriter();
//            // Set response content type
//            response.setContentType("text/html");
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Descarga Archivo</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>No Hay Archivos para descargar</h1>");
//            out.println("</body>");
//            out.println("</html>");
            LOG.log(Level.SEVERE, "Exception Download SILpy: ", e);
        }
    }

    /**
     * descargar un anexo por idProyecto
     *
     * @param idProyectoAdjunto
     * @return
     * @throws java.io.FileNotFoundException
     */
    public StreamedContent downloadExpediente(Integer idProyectoAdjunto) throws FileNotFoundException {
//        ProyectoAdjunto pa = proyectoAdjuntoFacade.find(idProyectoAdjunto);
//        File archivo = new File(JSFutil.getFolderExpediente() + pa.getIdProyectoAdjunto() + "-" + pa.getNombreArchivo());
//        if (archivo.exists()) {
//            StreamedContent file = new DefaultStreamedContent(new FileInputStream(archivo), pa.getTipoArchivo(), pa.toNameDownload());
//            return file;
//        } else {
//            System.out.println("ARCHIVO_NO_ENCONTRADO: " + pa.getClass() + " con ID " + pa.getIdProyectoAdjunto());
//            JSFutil.addMessage("No dispone de adjuntos para visualizar...", JSFutil.StatusMessage.WARNING);
//            String noContent = "<html><h1>Sin adjunto...</></html>";
//            return new DefaultStreamedContent(new ByteArrayInputStream(noContent.getBytes()), "text/html", "No existe Archivo");
//        }
        return null;
    }
}
