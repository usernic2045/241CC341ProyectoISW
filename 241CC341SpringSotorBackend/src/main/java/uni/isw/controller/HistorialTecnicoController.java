package uni.isw.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.model.HistorialTecnico;
import uni.isw.service.HistorialTecnicoService;

@RestController
@RequestMapping(path = "api/v1/historial_tecnico")
public class HistorialTecnicoController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HistorialTecnicoService historialTecnicoService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HistorialTecnico>> getHistorialesTecnicos() {
        List<HistorialTecnico> listaHistorialesTecnicos = null;
        try {
            listaHistorialesTecnicos = historialTecnicoService.getHistorialesTecnicos();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listaHistorialesTecnicos, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistorialTecnico> buscarHistorialTecnico(@RequestBody Optional<HistorialTecnico> historialTecnico) {
        try {
            historialTecnico = historialTecnicoService.getHistorialTecnico(historialTecnico.get().getId());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(historialTecnico.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistorialTecnico> insertarHistorialTecnico(@RequestBody HistorialTecnico historialTecnico) {
        try {
            historialTecnicoService.saveOrUpdateHistorialTecnico(historialTecnico);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(historialTecnico, HttpStatus.OK);
    }

    @RequestMapping(value = "/actualizar", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistorialTecnico> actualizarHistorialTecnico(@RequestBody HistorialTecnico historialTecnico) {
        try {
            historialTecnicoService.saveOrUpdateHistorialTecnico(historialTecnico);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(historialTecnico, HttpStatus.OK);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HistorialTecnico> eliminarHistorialTecnico(@RequestBody Optional<HistorialTecnico> historialTecnico) {
        try {
            historialTecnico = historialTecnicoService.getHistorialTecnico(historialTecnico.get().getId());
            if (historialTecnico.isPresent()) 
                historialTecnicoService.deleteHistorialTecnico(historialTecnico.get().getId());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(historialTecnico.get(), HttpStatus.OK);
    }
}

