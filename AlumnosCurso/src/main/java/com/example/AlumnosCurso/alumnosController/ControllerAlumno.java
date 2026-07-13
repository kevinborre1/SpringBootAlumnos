package com.example.AlumnosCurso.alumnosController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.AlumnosCurso.alumnosDominio.Alumno;


@RestController
@RequestMapping("/alumnos")
public class ControllerAlumno {

    private List<Alumno> alumnos = new ArrayList<>(Arrays.asList(
        new Alumno(1, "Juan", "juan@example", "20", "Ingeniería"),
        new Alumno(2, "María", "maria@example", "22", "Medicina"),
        new Alumno(3, "Pedro", "pedro@example", "25", "Derecho"),
        new Alumno(4, "Ana", "ana@example", "21", "Arquitectura")
    ));

    @RequestMapping(method = RequestMethod.GET)
    public List<Alumno> getAllAlumnos() {
        return alumnos;
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public Alumno getAlumnoByEmail(@PathVariable String email) {
        for (Alumno alumno : alumnos) {
            if (alumno.getEmail().equals(email)) {
                return alumno;
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Alumno postAlumno(Alumno alumno) {
        alumnos.add(alumno);
        return alumno;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Alumno deleteAlumno(@PathVariable int id){
        for (Alumno alumno : alumnos) {
            if (alumno.getID() == id) {
                alumnos.remove(alumno);
                return alumno;
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public Alumno patchAlumno (@RequestBody Alumno alumno) {
        for (Alumno a : alumnos) {
            if (a.getID() == alumno.getID()) {
                if (alumno.getNombre() != null) {
                    a.setNombre(alumno.getNombre());
                }
                if (alumno.getEmail() != null) {
                    a.setEmail(alumno.getEmail());
                }
                if (alumno.getEdad() != null) {
                    a.setEdad(alumno.getEdad());
                }
                if (alumno.getCurso() != null) {
                    a.setCurso(alumno.getCurso());
                }
                return a;
            }
        }
        return null;
    }



}