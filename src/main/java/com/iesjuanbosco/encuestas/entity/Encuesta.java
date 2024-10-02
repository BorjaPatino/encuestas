package com.iesjuanbosco.encuestas.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
@Table(name = "encuestas")
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres.")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios.")
    @Size(min = 2, message = "Los apellidos deben tener al menos 2 caracteres.")
    private String apellidos;

    @NotBlank(message = "El email es obligatorio.")
    @Email(message = "Formato de email inválido.")
    private String email;

    @NotNull(message = "La edad es obligatoria.")
    @Min(value = 18, message = "Debe tener al menos 18 años.")
    private Integer edad;

    @NotBlank(message = "El teléfono es obligatorio.")
    private String telefono;

    @NotNull(message = "La fecha de inicio de la estancia es obligatoria.")
    @PastOrPresent(message = "La fecha no puede ser futura.")
    private LocalDate fechaInicioEstancia;

    @NotBlank(message = "El motivo de la visita es obligatorio.")
    private String motivoVisita;

    private String[] serviciosUtilizados;

    @NotBlank(message = "Debe seleccionar un nivel de satisfacción.")
    private String nivelSatisfaccion;

    private String comentarios;

    // Constructor vacío
    public Encuesta() {
    }

    // Constructor con parámetros
    public Encuesta(Long id, String nombre, String apellidos, String email, Integer edad, String telefono,
                    LocalDate fechaInicioEstancia, String motivoVisita, String[] serviciosUtilizados,
                    String nivelSatisfaccion, String comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
        this.fechaInicioEstancia = fechaInicioEstancia;
        this.motivoVisita = motivoVisita;
        this.serviciosUtilizados = serviciosUtilizados;
        this.nivelSatisfaccion = nivelSatisfaccion;
        this.comentarios = comentarios;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaInicioEstancia() {
        return fechaInicioEstancia;
    }

    public void setFechaInicioEstancia(LocalDate fechaInicioEstancia) {
        this.fechaInicioEstancia = fechaInicioEstancia;
    }

    public String getMotivoVisita() {
        return motivoVisita;
    }

    public void setMotivoVisita(String motivoVisita) {
        this.motivoVisita = motivoVisita;
    }

    public String[] getServiciosUtilizados() {
        return serviciosUtilizados;
    }

    public void setServiciosUtilizados(String[] serviciosUtilizados) {
        this.serviciosUtilizados = serviciosUtilizados;
    }

    public String getNivelSatisfaccion() {
        return nivelSatisfaccion;
    }

    public void setNivelSatisfaccion(String nivelSatisfaccion) {
        this.nivelSatisfaccion = nivelSatisfaccion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Encuesta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", fechaInicioEstancia=" + fechaInicioEstancia +
                ", motivoVisita='" + motivoVisita + '\'' +
                ", serviciosUtilizados=" + Arrays.toString(serviciosUtilizados) +
                ", nivelSatisfaccion='" + nivelSatisfaccion + '\'' +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}
