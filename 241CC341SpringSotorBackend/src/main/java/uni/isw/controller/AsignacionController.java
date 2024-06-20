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
import uni.isw.model.Asignacion;
import uni.isw.service.AsignacionService;

@RestController
@RequestMapping(path = "api/v1/asignacion")
public class AsignacionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AsignacionService asignacionService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Asignacion>> getAsignaciones() {
        List<Asignacion> listaAsignaciones = null;
        try {
            listaAsignaciones = asignacionService.getAsignaciones();
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(listaAsignaciones, HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Asignacion> buscarAsignacion(@RequestBody Optional<Asignacion> asignacion) {
        try {
            asignacion = asignacionService.getAsignacion(asignacion.get().getId_asignacion());
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(asignacion.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Asignacion> insertarAsignacion(@RequestBody Asignacion asignacion) {
        try {
            asignacionService.saveOrUpdateAsignacion(asignacion);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(asignacion, HttpStatus.OK);
    }

    @RequestMapping(value = "/actualizar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Asignacion> actualizarAsignacion(@RequestBody Asignacion asignacion) {
        try {
            asignacionService.saveOrUpdateAsignacion(asignacion);
        } catch (Exception e) {
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(asignacion, HttpStatus.OK);
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Asignacion> eliminarAsignacion(@RequestBody Optional<Asignacion> asignacion) {
        try{
            asignacion=asignacionService.getAsignacion(asignacion.get().getId_asignacion());
            if(asignacion.isPresent())
                asignacionService.deleteAsignacion(asignacion.get().getId_asignacion());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(asignacion.get(),HttpStatus.OK);
    }
}
